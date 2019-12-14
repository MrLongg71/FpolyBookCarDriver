package vn.fpoly.fpolybookcardrive.model.statiscal;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.fpoly.fpolybookcardrive.Constans;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;
import vn.fpoly.fpolybookcardrive.presenter.statiscal.PresenterStatiscal;

public class ModelStatiscal {
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference dataOrder = FirebaseDatabase.getInstance().getReference();

    public void DowloadListOrderCar(final PresenterStatiscal presenterStatiscal, final int i) {
        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataOrderCar = dataSnapshot.child(Constans.childOrder).child(user.getUid());
                if (dataOrderCar.exists()) {
                    ArrayList<OrderCar> orderCarArrayList = new ArrayList<>();
                    ArrayList<OrderFood> orderFoodArrayList = new ArrayList<>();
                    for (DataSnapshot value : dataOrderCar.getChildren()) {
                        OrderCar orderCar = value.getValue(OrderCar.class);
                        orderCarArrayList.add(orderCar);

                        DataSnapshot dataOrderFood = dataSnapshot.child(Constans.childOrderFood).child(user.getUid());
                        if (dataOrderFood.exists()) {
                            for (DataSnapshot valueFood : dataOrderFood.getChildren()) {
                                OrderFood orderFood = valueFood.getValue(OrderFood.class);
                                orderFoodArrayList.add(orderFood);
                            }
                        }
                    }
                    presenterStatiscal.resultListOrder(true,orderCarArrayList,orderFoodArrayList,i);

                }else {
                    presenterStatiscal.resultListOrder(false,null,null,i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        dataOrder.addValueEventListener(valueEventListener);
    }

}
