package vn.fpoly.fpolybookcardrive.model.modebilldetail;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.fpoly.fpolybookcardrive.Constans;
import vn.fpoly.fpolybookcardrive.model.objectclass.BillFood;
import vn.fpoly.fpolybookcardrive.model.objectclass.FoodMenu;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;
import vn.fpoly.fpolybookcardrive.presenter.billdetail.PresenterBillDetail;
import vn.fpoly.fpolybookcardrive.presenter.orderfood.PresenterOrderFood;

public class ModelBillDetail {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ArrayList<BillFood> arrBillDetail = new ArrayList<>();
    private ArrayList<FoodMenu> arrFoodMenu = new ArrayList<>();

    public void dowloadListBillDetail(final String Uid, final String idOrderFood , final PresenterBillDetail presenterBillDetail){
        ValueEventListener dataOrder = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataOrderFood = dataSnapshot.child(Constans.childOrderFood).child(Uid).child(idOrderFood);
                OrderFood orderFood = dataOrderFood.getValue(OrderFood.class);
                DataSnapshot dataBillOrderFood = dataSnapshot.child(Constans.chilBillOrderFood).child(orderFood.getKeyBillDetail());

                for (DataSnapshot valueBillOrderFood : dataBillOrderFood.getChildren()){
                    BillFood billFood = valueBillOrderFood.getValue(BillFood.class);
                    DataSnapshot dataFoodMenu = dataSnapshot.child(Constans.chilFood).child(orderFood.getKeyRestaurant()).child(billFood.getKeyFood());
                    arrBillDetail.add(billFood);
                    FoodMenu foodMenu = dataFoodMenu.getValue(FoodMenu.class);
                    arrFoodMenu.add(foodMenu);

                }



                presenterBillDetail.resultBillDetail(arrFoodMenu,arrBillDetail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(dataOrder);
    }
}
