package vn.fpoly.fpolybookcardrive.view.splashscreen.driver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.activity.HomeActivity;
import vn.fpoly.fpolybookcardrive.library.Dialog;
import vn.fpoly.fpolybookcardrive.presenter.driver.PresenterLogin;


public class SigninFragment extends Fragment implements IViewLogin {
    private EditText tieEmail,tiePass;
    private PresenterLogin presenterLogin;
    private Button btnLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlogin, container, false);
        initView(view);
        tieEmail.setText("driver3@gmail.com");
        tiePass.setText("123456");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog.DialogLoading(getActivity(),true);
                checkValid();


            }
        });

        return view;
    }
    private void initView(View view){
        tieEmail        = view.findViewById(R.id.edtEmailLogin);
        tiePass         = view.findViewById(R.id.edtPassLogin);
        presenterLogin  = new PresenterLogin(this);
        btnLogin        = view.findViewById(R.id.btnLoginHome);
    }

    @Override
    public void onSuccess() {
        Dialog.DialogLoading(getActivity(),false);

//        String Uid =  firebaseAuth.getCurrentUser().getUid();
//        Intent intent = new Intent(getActivity(),HomeActivity.class);
//        intent.putExtra("Uid",Uid);
//        startActivity(intent);
//        getActivity().finish();

        Intent intent = new Intent(getActivity(),HomeActivity.class);
        startActivity(intent);
        getActivity().finish();


    }

    @Override
    public void onFail(String message) {

        Dialog.DialogLoading(getActivity(),false);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }
    private boolean checkValid(){
        String Email = tieEmail.getText().toString().trim();
        String Pass  = tiePass.getText().toString().trim();

        if (Email.length() == 0 ){
            tieEmail.setError("Please enter iconapp username,phone");
            tieEmail.requestFocus();
            return true;
        }else if (Pass.length() == 0 ){
            tiePass.setError("Please enter iconapp password");
            tiePass.requestFocus();
            return true;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            tieEmail.setError("Please enter iconapp valid email address");
            tieEmail.requestFocus();
            return true;
        }else if (Pass.length() < 6 ){
            tiePass.setError("Password must have at least 6 special character");
            tiePass.requestFocus();
            return true;
        }else {
            presenterLogin.doSignin(Email,Pass);

        }

        return false;
    }

    @Override
    public void onDestroy() {
        Dialog.DialogLoading(getActivity(),false);
        super.onDestroy();

    }


    @Override
    public void onPause() {
        Dialog.DialogLoading(getActivity(),false);
        super.onPause();

    }
}
