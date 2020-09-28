package vn.fpoly.fpolybookcardrive.model.modeldriver;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vn.fpoly.fpolybookcardrive.Constans;
import vn.fpoly.fpolybookcardrive.model.objectclass.Driver;
import vn.fpoly.fpolybookcardrive.presenter.account.PresenterAccount;

public class ModelDriver {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public  void dowloadAccountDriver(final String Uid, final PresenterAccount presenterAccount){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataDriver = dataSnapshot.child(Constans.childDriver).child("Car").child(Uid);
                Driver driver = dataDriver.getValue(Driver.class);
                presenterAccount.resultDriver(driver);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

}
