package vn.fpoly.fpolybookcardrive.view.splashscreen.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.fpoly.fpolybookcardrive.R;

public class FragmentPayFood extends Fragment {
    LinearLayout lineTotalAmount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paycar, container, false);
        initView(view);
        lineTotalAmount.setVisibility(View.VISIBLE);
        return view;
    }
    private void initView(View view){
        lineTotalAmount = view.findViewById(R.id.lineTotalAmount);
    }
}
