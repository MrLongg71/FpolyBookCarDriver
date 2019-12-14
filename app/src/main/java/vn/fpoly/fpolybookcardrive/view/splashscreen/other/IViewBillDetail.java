package vn.fpoly.fpolybookcardrive.view.splashscreen.other;

import java.util.ArrayList;

import vn.fpoly.fpolybookcardrive.model.objectclass.BillFood;
import vn.fpoly.fpolybookcardrive.model.objectclass.FoodMenu;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;

public interface IViewBillDetail {
    void displayBillDetail(ArrayList<BillFood>arrBillDetail, ArrayList<FoodMenu>arrFoodMenu);
}
