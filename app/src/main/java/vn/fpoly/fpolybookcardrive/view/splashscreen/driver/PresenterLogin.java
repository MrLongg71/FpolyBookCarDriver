package vn.fpoly.fpolybookcardrive.view.splashscreen.driver;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PresenterLogin implements IPresenterLogin {
    private IViewLogin iViewLogin;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public PresenterLogin(IViewLogin iViewLogin) {
        this.iViewLogin = iViewLogin;

    }

    @Override
    public void doSignin(String email, String passs) {

        firebaseAuth.signInWithEmailAndPassword(email,passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            DataSnapshot rule = dataSnapshot.child("Client").child(firebaseAuth.getCurrentUser().getUid());
                            if(rule.exists()){
                                iViewLogin.onFail("Mày có phải tài xế đâu mà login hả Lâm VĂn Mậ");
                            }else {
                                iViewLogin.onSuccess();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    databaseReference.addValueEventListener(valueEventListener);

                }else {
                    iViewLogin.onFail(task.getException().getMessage());
                }
            }
        });

    }
}
