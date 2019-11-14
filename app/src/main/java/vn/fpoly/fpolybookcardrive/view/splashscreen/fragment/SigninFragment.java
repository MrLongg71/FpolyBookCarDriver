package vn.fpoly.fpolybookcardrive.view.splashscreen.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;
import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.Activity.HomeActivity;
import vn.fpoly.fpolybookcardrive.library.Dialog;
import vn.fpoly.fpolybookcardrive.model.objectclass.Driver;
import vn.fpoly.fpolybookcardrive.view.splashscreen.IViewLogin;
import vn.fpoly.fpolybookcardrive.view.splashscreen.PresenterLogin;


public class SigninFragment extends Fragment implements IViewLogin {
    private TextInputEditText tieEmail,tiePass;
    private PresenterLogin presenterLogin;
    private Button btnLogin,btnregister;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private EditText edtuser,edtpass;
    private SpotsDialog spotsDialog;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
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
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final   String ten = edtuser.getText().toString();
                String pass = edtpass.getText().toString();
                Log.d("kiemtranhan","vo");
                firebaseAuth.createUserWithEmailAndPassword(ten,pass).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("kiemtranhan","vo1");
                        if (task.isSuccessful()){
                            Log.d("kiemtranhan","thanhcong");
                    databaseReference.child("Driver").child("Car").child(firebaseAuth.getCurrentUser().getUid()).setValue(new Driver(firebaseAuth.getCurrentUser().getUid(),ten,"abc","123","456",5.0,1.5,5,5,13,"",true,false));
                            Toast.makeText(getActivity(), "cc", Toast.LENGTH_SHORT).show();
                        }else {
                            Log.d("kiemtra","fail");
                        }
                    }
                });
            }
        });
        return view;
    }
    private void initView(View view){
        tieEmail        = view.findViewById(R.id.edtEmailLogin);
        tiePass         = view.findViewById(R.id.edtPassLogin);
        presenterLogin  = new PresenterLogin(this);
        btnLogin        = view.findViewById(R.id.btnLoginHome);
        edtpass         = view.findViewById(R.id.edtpass1);
        edtuser         = view.findViewById(R.id.edtusr);
        btnregister     = view.findViewById(R.id.btndangky);
    }

    @Override
    public void onSuccess() {
        Dialog.DialogLoading(getActivity(),true);
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }

    @Override
    public void onFail() {
        Dialog.DialogLoading(getActivity(),true);

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

}
