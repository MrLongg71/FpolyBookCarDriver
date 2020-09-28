package vn.fpoly.fpolybookcardrive.model.objectclass;

public class NotificationRV {
    private String idClient,idOrder,event;

    public NotificationRV(String idClient, String idOrder, String event) {
        this.idClient = idClient;
        this.idOrder = idOrder;
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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
