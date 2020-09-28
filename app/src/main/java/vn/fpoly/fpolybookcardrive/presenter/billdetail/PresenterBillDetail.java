package vn.fpoly.fpolybookcardrive.presenter.billdetail;


import java.util.ArrayList;

import vn.fpoly.fpolybookcardrive.model.modebilldetail.ModelBillDetail;
import vn.fpoly.fpolybookcardrive.model.modelorderfood.ModelOrderFood;
import vn.fpoly.fpolybookcardrive.model.objectclass.BillFood;
import vn.fpoly.fpolybookcardrive.model.objectclass.FoodMenu;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;
import vn.fpoly.fpolybookcardrive.view.splashscreen.home.IViewOrderFood;
import vn.fpoly.fpolybookcardrive.view.splashscreen.other.IViewBillDetail;

public  class PresenterBillDetail implements IPresenterBillDetail {
    private ModelBillDetail modelBillDetaile;
    private IViewBillDetail iViewBillDetail;

    public PresenterBillDetail(IViewBillDetail iViewBillDetail) {
        this.iViewBillDetail = iViewBillDetail;
        modelBillDetaile = new ModelBillDetail();
    }

    @Override
    public void getOderBillDetail(String Uid, String idOrderFood) {
        modelBillDetaile.dowloadListBillDetail(Uid,idOrderFood,this);
    }

    @Override
    public void resultBillDetail(ArrayList<FoodMenu>arrFoodMenu,ArrayList<BillFood> arrBillDetail) {
        iViewBillDetail.displayBillDetail(arrBillDetail,arrFoodMenu);
    }
}
