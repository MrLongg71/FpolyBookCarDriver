package vn.fpoly.fpolybookcardrive.model.modelorder;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.concurrent.ExecutionException;

import vn.fpoly.fpolybookcardrive.Constans;
import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.presenter.maps.PresenterGoogleMap;
import vn.fpoly.fpolybookcardrive.service.DowloadPolyLine;
import vn.fpoly.fpolybookcardrive.service.ParsePolyline;

public class ModelOrder {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public void dowloadPoylineList(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome, PresenterGoogleMap presenterGoogleMap) {
        String LINK = Constans.LINK_GOOGLE_API_DRAW_POLYLINE + locationGo.latitude + Constans.Comma + locationGo.longitude + Constans.Destination + locationCome.latitude + Constans.Comma + locationCome.longitude + Constans.Language + activity.getString(R.string.google_map_parseJson);
        DowloadPolyLine dowloadPolyLine = new DowloadPolyLine();
        dowloadPolyLine.execute(LINK);
        try {

            String dataJSON = dowloadPolyLine.get();
            List<LatLng> latLngList = ParsePolyline.getListLocation(dataJSON);
            PolylineOptions polylineOptions = new PolylineOptions();
            for (LatLng latLng : latLngList) {
                polylineOptions.add(latLng);

            }
            Polyline polyline = googleMap.addPolyline(polylineOptions);
            polyline.setColor(Color.rgb(72,155,250));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dowloadOrderList(final String idOrder, final String Uid, final PresenterGoogleMap presenterGoogleMap) {
        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot nodeOrder = dataSnapshot.child("Order").child(Uid).child(idOrder);
                OrderCar orderCar = nodeOrder.getValue(OrderCar.class);
                DataSnapshot valuteNameCustomer = dataSnapshot.child("Client").child(orderCar.getKeyclient()).child("name");

                String nameCustomer = valuteNameCustomer.getValue(String.class);
                presenterGoogleMap.resultOrderCar(orderCar,nameCustomer);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };


        databaseReference.addValueEventListener(valueEventListener);
    }
}
