package vn.fpoly.fpolybookcardrive.view.splashscreen.statistical;

import java.util.ArrayList;

import vn.fpoly.fpolybookcardrive.model.objectclass.OrderCar;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;

public interface IViewStatistical {
    void displayListOrderDay(int total_Jobs, int total_money, ArrayList<OrderCar> orderCarArrayList, ArrayList<OrderFood> orderFoodArrayList);
}
