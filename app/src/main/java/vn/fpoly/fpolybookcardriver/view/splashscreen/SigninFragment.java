package vn.fpoly.fpolybookcardriver.view.splashscreen;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import vn.fpoly.fpolybookcardriver.R;

public class SigninFragment extends Fragment implements IViewLogin {
    private TextInputEditText tieEmail,tiePass;
    private PresenterLogin presenterLogin;
    private Button btnLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlogin, container, false);
        initView(view);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    }

    @Override
    public void onFail() {

    }
    private boolean checkValid(){
        String Email = tieEmail.getText().toString().trim();
        String Pass  = tiePass.getText().toString().trim();
        if (Email.length() == 0 ){
            tieEmail.setError("Please enter a username,phone");
            tieEmail.requestFocus();
            return true;
        }else if (Pass.length() == 0 ){
            tiePass.setError("Please enter a password");
            tiePass.requestFocus();
            return true;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            tieEmail.setError("Please enter a valid email address");
            tieEmail.requestFocus();
            return true;
        }else if (Pass.length() < 6 ){
            tiePass.setError("Password must have at least 6 special character");
            tiePass.requestFocus();
            return true;
        }else {
            presenterLogin.doSignin(Email,Pass);
            Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
