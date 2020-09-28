package vn.fpoly.fpolybookcardrive.presenter.account;

import vn.fpoly.fpolybookcardrive.model.modeldriver.ModelDriver;
import vn.fpoly.fpolybookcardrive.model.objectclass.Driver;
import vn.fpoly.fpolybookcardrive.view.splashscreen.account.IViewAccount;

public class PresenterAccount implements  IPresenterAccount{
    private ModelDriver modelDriver;
    private IViewAccount iViewAccount;

    public PresenterAccount(IViewAccount iViewAccount) {
        this.iViewAccount = iViewAccount;
        modelDriver = new ModelDriver();
    }

    @Override
    public void getDriver(String Uid) {
        modelDriver.dowloadAccountDriver(Uid,this);
    }

    @Override
    public void resultDriver(Driver driver) {
        iViewAccount.displayDriver(driver);
    }
}
