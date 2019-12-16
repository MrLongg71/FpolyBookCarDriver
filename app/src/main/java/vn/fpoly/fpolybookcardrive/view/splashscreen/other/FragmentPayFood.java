package vn.fpoly.fpolybookcardrive.view.splashscreen.other;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.fpoly.fpolybookcardrive.Constans;
import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;
import vn.fpoly.fpolybookcardrive.presenter.orderfood.PresenterOrderFood;
import vn.fpoly.fpolybookcardrive.view.splashscreen.home.IViewOrderFood;

public class FragmentPayFood extends Fragment implements IViewOrderFood {
    private LinearLayout lineTotalAmount;
    private TextView txtTotalGoodQuanlity,txtTotalBill,txtPrice,txtNameCustomer,
            txtReceiveConclusion,txtDestinationConclusion,txtDate,txtDistanceConclusion;
    private PresenterOrderFood presenterOrderFood;
    private Button btnProceed;
    private String Uid;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {
        View view = inflater.inflate(R.layout.fragment_paycar, container, false);
        initView(view);
        lineTotalAmount.setVisibility(View.VISIBLE);
        Bundle bundle = getArguments();
        if(bundle!=null){
            String amountGoods  = bundle.getString("amountGoods");
            String total        = bundle.getString("total");
            Uid                 = bundle.getString("Uid");
            String idOrder      = bundle.getString("idOrder");
            txtTotalGoodQuanlity.setText(amountGoods);
            txtTotalBill.setText(total+"K");
            txtPrice.setText(total+"K");
            presenterOrderFood.getOrderFood(Uid,idOrder);

        }
        return view;
    }
    private void initView(View view){
        lineTotalAmount                 = view.findViewById(R.id.lineTotalAmount);
        txtTotalGoodQuanlity            = view.findViewById(R.id.txtTotalGoodQuanlity);
        txtTotalBill                    = view.findViewById(R.id.txtTotalConclusion);
        txtPrice                        = view.findViewById(R.id.txtPriceConclusion);
        txtDestinationConclusion        = view.findViewById(R.id.txtDestinationConclusion);
        txtDistanceConclusion           = view.findViewById(R.id.txtDistanceConclusion);
        txtNameCustomer                 = view.findViewById(R.id.txtNameCustomer);
        txtDate                         = view.findViewById(R.id.txtDate);
        txtReceiveConclusion            = view.findViewById(R.id.txtReceiveConclusion);
        presenterOrderFood = new PresenterOrderFood(this);
        btnProceed                      = view.findViewById(R.id.btnProceedConclusion);

    }

    @Override
    public void displayOrderFood(final OrderFood orderFood, String nameRestaurant, String nameCustomer) {
        txtNameCustomer.setText(nameCustomer);
        txtReceiveConclusion.setText(orderFood.getPlaceNameRes());
        txtDestinationConclusion.setText(orderFood.getPlaceNameC());

        txtDistanceConclusion.setText(orderFood.getDistance()+"Km");
        txtDate.setText(orderFood.getDate());
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(Constans.childOrderFood).child(Uid).child(orderFood.getKeyBillDetail()).child("finish").setValue(true);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }
}
