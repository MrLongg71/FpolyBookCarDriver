package vn.fpoly.fpolybookcardrive.view.splashscreen.account;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.library.CallBackFragment;
import vn.fpoly.fpolybookcardrive.model.objectclass.Driver;
import vn.fpoly.fpolybookcardrive.presenter.account.PresenterAccount;

public class FragmentAccount extends Fragment implements IViewAccount {
    private FragmentManager fragmentManager;
    private TextView txtNameDriver,txtRateDriver,txtPhoneDriver,txtLinceDrivver,txtEmailDriver;
    private PresenterAccount presenterAccount;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showmore, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        CallBackFragment.CallbackHome(view,fragmentManager);
        initView(view);
        presenterAccount.getDriver(firebaseAuth.getUid());
        return view;
    }
    private void initView(View view){
        txtLinceDrivver     = view.findViewById(R.id.txtLinse);
        txtEmailDriver      = view.findViewById(R.id.txtEmailDriver);
        txtRateDriver       = view.findViewById(R.id.txtRateDriver);
        txtNameDriver       = view.findViewById(R.id.txtNameDriver);
        txtPhoneDriver      = view.findViewById(R.id.txtPhoneDriver);
        presenterAccount    = new PresenterAccount(this);
    }

    @Override
    public void displayDriver(Driver driver) {
       txtNameDriver.setText(driver.getName());
       txtEmailDriver.setText(driver.getEmail());
       txtLinceDrivver.setText(driver.getLicenseplate());
       txtPhoneDriver.setText(driver.getPhone());
       txtRateDriver.setText(driver.getRate()+"");
    }
}
