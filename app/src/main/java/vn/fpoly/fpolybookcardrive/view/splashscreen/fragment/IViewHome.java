package vn.fpoly.fpolybookcardrive.view.splashscreen.fragment;

import com.google.firebase.auth.FirebaseAuth;

import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;

public interface IViewHome {
    void drawPolyline();
    void displayOrder(OrderCar orderCar);
}
