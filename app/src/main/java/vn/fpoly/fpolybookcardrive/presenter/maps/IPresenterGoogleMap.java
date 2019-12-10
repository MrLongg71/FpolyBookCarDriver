package vn.fpoly.fpolybookcardrive.presenter.maps;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;

import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;

public interface IPresenterGoogleMap {

    void getPolyline(Activity activity,GoogleMap googleMap,LatLng locationGo,LatLng locationCome);
    void getOrderCar(String idOrder,String Uid);
    void resultOrderCar(OrderCar orderCar,String nameCustomer);

}
