package vn.fpoly.fpolybookcardrive.view.splashscreen.home;

import com.google.firebase.auth.FirebaseAuth;

import vn.fpoly.fpolybookcardrive.model.objectclass.Driver;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;

public interface IViewHome {
    void drawPolyline();
    void displayOrder(OrderCar orderCar, String nameCustomer,  String phoneCustomer);
}
