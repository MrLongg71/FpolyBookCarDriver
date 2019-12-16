package vn.fpoly.fpolybookcardrive.model.objectclass;

public class NotificationRV {
    private String idClient,idOrder;

    public NotificationRV(String idClient, String idOrder) {
        this.idClient = idClient;
        this.idOrder = idOrder;
    }

    public NotificationRV() {
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }
}
