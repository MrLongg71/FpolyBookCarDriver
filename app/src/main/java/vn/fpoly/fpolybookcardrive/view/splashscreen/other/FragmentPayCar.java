package vn.fpoly.fpolybookcardrive.view.splashscreen.other;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import java.util.Objects;

import vn.fpoly.fpolybookcardrive.activity.HomeActivity;
import vn.fpoly.fpolybookcardrive.Constans;
import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.view.splashscreen.home.FragmentHome;

public class FragmentPayCar extends Fragment{
    private TextView txtNameCustomer,txtRecive,txtDestination,txtDistance,txtPrice,txtTotal,txtDate;
    private Button btnProceed;
    private LinearLayout lineTotalAmout;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paycar, container, false);
        initView(view);
        lineTotalAmout.setVisibility(View.GONE);
        HomeActivity.bottomNavigationView.setVisibility(View.GONE);
        OrderCar orderCar;
        Bundle bundle = getArguments();
        if (bundle != null){
            String nameCustomer = bundle.getString(Constans.KEY_BUNDEL_NAMECUSTOMER);
            orderCar = bundle.getParcelable(Constans.KEY_BUNDEL_ORDER);
            txtRecive.setText(Objects.requireNonNull(orderCar).getPlacenamego());
            txtDestination.setText(orderCar.getPlacenamecome());
            txtDistance.setText(orderCar.getDistance()+" Km");
            txtPrice.setText(orderCar.getPrice()+"K");
            txtNameCustomer.setText(nameCustomer);
            txtDate.setText(orderCar.getDate());
            txtTotal.setText(orderCar.getPrice()+" VND");
        }
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_home,new FragmentHome()).commit();

            }
        });

        return view;
    }
    private void initView(View view){
        txtNameCustomer = view.findViewById(R.id.txtNameCustomer);
        txtDestination  = view.findViewById(R.id.txtDestinationConclusion);
        txtDistance     = view.findViewById(R.id.txtDistanceConclusion);
        txtTotal        = view.findViewById(R.id.txtTotalConclusion);
        txtRecive       = view.findViewById(R.id.txtReceiveConclusion);
        txtPrice        = view.findViewById(R.id.txtPriceConclusion);
        btnProceed      = view.findViewById(R.id.btnProceedConclusion);
        txtDate         = view.findViewById(R.id.txtDate);
        btnProceed      = view.findViewById(R.id.btnProceedConclusion);
        lineTotalAmout  = view.findViewById(R.id.lineTotalAmount);
    }


}
