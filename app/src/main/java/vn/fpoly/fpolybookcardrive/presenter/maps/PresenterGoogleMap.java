package vn.fpoly.fpolybookcardrive.presenter.maps;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import vn.fpoly.fpolybookcardrive.model.modelorder.ModelOrder;
import vn.fpoly.fpolybookcardrive.model.objectclass.Driver;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.view.splashscreen.home.IViewHome;

public class PresenterGoogleMap  implements  IPresenterGoogleMap{
   private ModelOrder modelOrder;
    private IViewHome ivIewHome;

    public PresenterGoogleMap(IViewHome ivIewHome) {
        this.ivIewHome = ivIewHome;
        modelOrder = new ModelOrder();
    }

    @Override
    public void getPolyline(Activity activity, GoogleMap googleMap, LatLng locationGo, LatLng locationCome) {
        modelOrder.dowloadPoylineList(activity,googleMap,locationGo,locationCome,this);
    }

    @Override
    public void getOrderCar(String idOrder,String Uid) {
        modelOrder.dowloadOrderList(idOrder,Uid,this);
    }


    @Override
    public void resultOrderCar(OrderCar orderCar, String nameCustomer) {
        ivIewHome.displayOrder(orderCar,nameCustomer);
    }


}
