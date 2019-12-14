package vn.fpoly.fpolybookcardrive.presenter.orderfood;

import java.util.ArrayList;

import vn.fpoly.fpolybookcardrive.model.objectclass.BillFood;
import vn.fpoly.fpolybookcardrive.model.objectclass.FoodMenu;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;

public interface IPresenterOrderFood {
    void getOrderFood(String Uid,String idOrderFood);
    void resultOrderFood(OrderFood orderFood,String nameCustomer,String nameRestaurant);

}
