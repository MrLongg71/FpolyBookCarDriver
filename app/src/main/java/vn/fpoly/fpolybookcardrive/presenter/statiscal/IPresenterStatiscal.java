package vn.fpoly.fpolybookcardrive.presenter.statiscal;

import java.util.ArrayList;

import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;

public interface IPresenterStatiscal {
    void getListOrder(int i);
    void resultListOrder(boolean success, ArrayList<OrderCar> orderCars, ArrayList<OrderFood> orderFoods,int i);


}
