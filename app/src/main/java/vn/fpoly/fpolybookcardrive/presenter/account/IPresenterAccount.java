package vn.fpoly.fpolybookcardrive.presenter.account;

import vn.fpoly.fpolybookcardrive.model.objectclass.Driver;

public interface IPresenterAccount {
    void getDriver(String Uid);
    void resultDriver(Driver driver);
}
