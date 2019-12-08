package vn.fpoly.fpolybookcardrive.view.splashscreen;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PresenterLogin implements IPresenterLogin {
    private IViewLogin iViewLogin;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public PresenterLogin(IViewLogin iViewLogin) {
        this.iViewLogin = iViewLogin;

    }

    @Override
    public void doSignin(String email, String passs) {

        firebaseAuth.signInWithEmailAndPassword(email,passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    iViewLogin.onSuccess();
                }else {
                    iViewLogin.onFail();
                }
            }
        });

    }
}
