package vn.fpoly.fpolybookcardrive.view.splashscreen.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.fpoly.fpolybookcardrive.R;

public class FragmentOrderfood extends Fragment {
    private TextView txtDate,txtNameRestaurant,txtTotal,txtAddress,txtDistanceCustomer;
    private Button btnOrderFood;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderfood, container, false);
        initView(view);
        return view;
    }
    private void initView(View view){
        txtDistanceCustomer     = view.findViewById(R.id.txtDistanceOrderFood);
        txtNameRestaurant       = view.findViewById(R.id.txtNameRestaurantOrderFood);
        txtDate                 = view.findViewById(R.id.txtDate);
        txtTotal                = view.findViewById(R.id.txtTotalOrderFood);
        txtAddress              = view.findViewById(R.id.txtAddressOrderFood);
        btnOrderFood            = view.findViewById(R.id.btnOrderFood);
    }
}
