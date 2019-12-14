package vn.fpoly.fpolybookcardrive.presenter.orderfood;


import vn.fpoly.fpolybookcardrive.model.modelorderfood.ModelOrderFood;
import vn.fpoly.fpolybookcardrive.model.objectclass.OrderFood;
import vn.fpoly.fpolybookcardrive.view.splashscreen.home.IViewOrderFood;

public  class PresenterOrderFood implements IPresenterOrderFood {
    private ModelOrderFood modelOrderFood;
    private IViewOrderFood iViewOrderFood;

    public PresenterOrderFood(IViewOrderFood iViewOrderFood) {
        this.iViewOrderFood = iViewOrderFood;
        modelOrderFood = new ModelOrderFood();
    }

    @Override
    public void getOrderFood(String Uid, String idOrderFood) {
        modelOrderFood.dowloadListOrderFood(Uid,idOrderFood,this);
    }

    @Override
    public void resultOrderFood(OrderFood orderFood, String nameRestaurant, String nameCustomer) {
        iViewOrderFood.displayOrderFood(orderFood,nameRestaurant,nameCustomer);
    }
}
