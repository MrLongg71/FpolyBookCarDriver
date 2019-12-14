package vn.fpoly.fpolybookcardrive.presenter.billdetail;

import java.util.ArrayList;

import vn.fpoly.fpolybookcardrive.model.objectclass.BillFood;
import vn.fpoly.fpolybookcardrive.model.objectclass.FoodMenu;

public interface IPresenterBillDetail {
    void getOderBillDetail(String Uid, String idOrderFood);
    void resultBillDetail(ArrayList<FoodMenu> arrFoodMenu, ArrayList<BillFood> arrBillDetail);

}
