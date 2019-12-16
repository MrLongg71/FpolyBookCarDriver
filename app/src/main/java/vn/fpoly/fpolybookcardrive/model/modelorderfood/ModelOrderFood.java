package vn.fpoly.fpolybookcardrive.model.modelorderfood;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vn.fpoly.fpolybookcardrive.Constans;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;
import vn.fpoly.fpolybookcardrive.presenter.orderfood.PresenterOrderFood;

public class ModelOrderFood {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public void dowloadListOrderFood(final String Uid, final String idOrderFood , final PresenterOrderFood presenterBillDetail){
        ValueEventListener dataOrder = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataOrderFood = dataSnapshot.child(Constans.childOrderFood).child(Uid).child(idOrderFood);
                OrderFood orderFood = dataOrderFood.getValue(OrderFood.class);
                DataSnapshot dataRetaurant = dataSnapshot.child(Constans.childRestaurant).child(orderFood.getKeyRestaurant()).child("name");
                DataSnapshot dataClient = dataSnapshot.child(Constans.childClient).child(orderFood.getKeyClient()).child("name");

                String nameCustomer = dataClient.getValue(String.class);
                String nameRestaurant = dataRetaurant.getValue(String.class);

                presenterBillDetail.resultOrderFood(orderFood,nameRestaurant,nameCustomer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(dataOrder);
    }

}
