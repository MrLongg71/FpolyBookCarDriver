package vn.fpoly.fpolybookcardrive.presenter.maps;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import vn.fpoly.fpolybookcardrive.model.modelordercar.ModelOrderCar;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.view.splashscreen.home.IViewHome;

public class PresenterGoogleMap  implements  IPresenterGoogleMap{
   private ModelOrderCar modelOrderCar;
    private IViewHome ivIewHome;

    public PresenterGoogleMap(IViewHome ivIewHome) {
        this.ivIewHome = ivIewHome;
        modelOrderCar = new ModelOrderCar();
    }

    @Override
    public void getPolyline(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome) {
        modelOrderCar.dowloadPoylineList(activity,googleMap,locationGo,locationCome,this);
    }

    @Override
    public void getOrderCar(String idOrder,String Uid) {
        modelOrderCar.dowloadOrderList(idOrder,Uid,this);
    }


    @Override
    public void resultOrderCar(OrderCar orderCar, String nameCustomer, String phoneCustomer) {
        ivIewHome.displayOrder(orderCar,nameCustomer,phoneCustomer);
    }


}
