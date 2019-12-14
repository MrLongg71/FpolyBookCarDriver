package vn.fpoly.fpolybookcardrive.model.objectclass;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderFood implements Parcelable {
    private String key,keyRestaurant,keyDriver,keyClient,keyBillDetail,date,placeNameRes,placeNameC;
    private double latitudeClient,longitudeClient,price,rate,distance;
    private boolean status,finish;

    public OrderFood(String key, String keyRestaurant, String keyDriver, String keyClient, String keyBillDetail, String date, String placeNameRes, String placeNameC, double latitudeClient, double longitudeClient, double price, double rate, double distance, boolean status, boolean finish) {
        this.key = key;
        this.keyRestaurant = keyRestaurant;
        this.keyDriver = keyDriver;
        this.keyClient = keyClient;
        this.keyBillDetail = keyBillDetail;
        this.date = date;
        this.placeNameRes = placeNameRes;
        this.placeNameC = placeNameC;
        this.latitudeClient = latitudeClient;
        this.longitudeClient = longitudeClient;
        this.price = price;
        this.rate = rate;
        this.distance = distance;
        this.status = status;
        this.finish = finish;
    }

    public OrderFood() {
    }

    protected OrderFood(Parcel in) {
        key = in.readString();
        keyRestaurant = in.readString();
        keyDriver = in.readString();
        keyClient = in.readString();
        keyBillDetail = in.readString();
        date = in.readString();
        placeNameRes = in.readString();
        placeNameC = in.readString();
        latitudeClient = in.readDouble();
        longitudeClient = in.readDouble();
        price = in.readDouble();
        rate = in.readDouble();
        distance = in.readDouble();
        status = in.readByte() != 0;
        finish = in.readByte() != 0;
    }

    public static final Creator<OrderFood> CREATOR = new Creator<OrderFood>() {
        @Override
        public OrderFood createFromParcel(Parcel in) {
            return new OrderFood(in);
        }

        @Override
        public OrderFood[] newArray(int size) {
            return new OrderFood[size];
        }
    };

    public String getPlaceNameRes() {
        return placeNameRes;
    }

    public void setPlaceNameRes(String placeNameRes) {
        this.placeNameRes = placeNameRes;
    }

    public String getPlaceNameC() {
        return placeNameC;
    }

    public void setPlaceNameC(String placeNameC) {
        this.placeNameC = placeNameC;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public String getKeyBillDetail() {
        return keyBillDetail;
    }

    public void setKeyBillDetail(String keyBillDetail) {
        this.keyBillDetail = keyBillDetail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyRestaurant() {
        return keyRestaurant;
    }

    public void setKeyRestaurant(String keyRestaurant) {
        this.keyRestaurant = keyRestaurant;
    }

    public String getKeyDriver() {
        return keyDriver;
    }

    public void setKeyDriver(String keyDriver) {
        this.keyDriver = keyDriver;
    }

    public String getKeyClient() {
        return keyClient;
    }

    public void setKeyClient(String keyClient) {
        this.keyClient = keyClient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLatitudeClient() {
        return latitudeClient;
    }

    public void setLatitudeClient(double latitudeClient) {
        this.latitudeClient = latitudeClient;
    }

    public double getLongitudeClient() {
        return longitudeClient;
    }

    public void setLongitudeClient(double longitudeClient) {
        this.longitudeClient = longitudeClient;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(keyRestaurant);
        parcel.writeString(keyDriver);
        parcel.writeString(keyClient);
        parcel.writeString(keyBillDetail);
        parcel.writeString(date);
        parcel.writeString(placeNameRes);
        parcel.writeString(placeNameC);
        parcel.writeDouble(latitudeClient);
        parcel.writeDouble(longitudeClient);
        parcel.writeDouble(price);
        parcel.writeDouble(rate);
        parcel.writeDouble(distance);
        parcel.writeByte((byte) (status ? 1 : 0));
        parcel.writeByte((byte) (finish ? 1 : 0));
    }
}
