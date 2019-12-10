package vn.fpoly.fpolybookcardrive.model.objectclass;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderCar implements Parcelable {
    private String keyOrder,keyclient,keydriver,placenamego,placenamecome,date;
    private double latitudego,longitudego,latitudecome,longitudecome,price,rate,distance;
    private boolean status,fisnish;

    public OrderCar() {
    }

    public OrderCar(String keyOrder, String keyclient, String keydriver , String placenamego, String placenamecome, String date, double latitudego, double longitudego, double latitudecome, double longitudecome, double price, double rate, double distance, boolean status, boolean fisnish) {
        this.keyclient = keyclient;
        this.placenamego = placenamego;
        this.placenamecome = placenamecome;
        this.date = date;
        this.latitudego = latitudego;
        this.longitudego = longitudego;
        this.latitudecome = latitudecome;
        this.longitudecome = longitudecome;
        this.price = price;
        this.rate = rate;
        this.distance = distance;
        this.status = status;
        this.fisnish = fisnish;
        this.keydriver = keydriver;
        this.keyOrder = keyOrder;
    }

    protected OrderCar(Parcel in) {
        keyOrder = in.readString();
        keyclient = in.readString();
        keydriver = in.readString();
        placenamego = in.readString();
        placenamecome = in.readString();
        date = in.readString();
        latitudego = in.readDouble();
        longitudego = in.readDouble();
        latitudecome = in.readDouble();
        longitudecome = in.readDouble();
        price = in.readDouble();
        rate = in.readDouble();
        distance = in.readDouble();
        status = in.readByte() != 0;
        fisnish = in.readByte() != 0;
    }

    public static final Creator<OrderCar> CREATOR = new Creator<OrderCar>() {
        @Override
        public OrderCar createFromParcel(Parcel in) {
            return new OrderCar(in);
        }

        @Override
        public OrderCar[] newArray(int size) {
            return new OrderCar[size];
        }
    };

    public String getKeyOrder() {
        return keyOrder;
    }

    public void setKeyOrder(String keyOrder) {
        this.keyOrder = keyOrder;
    }

    public String getKeydriver() {
        return keydriver;
    }

    public void setKeydriver(String keydriver) {
        this.keydriver = keydriver;
    }

    public String getKeyclient() {
        return keyclient;
    }

    public void setKeyclient(String keyclient) {
        this.keyclient = keyclient;
    }

    public String getPlacenamego() {
        return placenamego;
    }

    public void setPlacenamego(String placenamego) {
        this.placenamego = placenamego;
    }

    public String getPlacenamecome() {
        return placenamecome;
    }

    public void setPlacenamecome(String placenamecome) {
        this.placenamecome = placenamecome;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLatitudego() {
        return latitudego;
    }

    public void setLatitudego(double latitudego) {
        this.latitudego = latitudego;
    }

    public double getLongitudego() {
        return longitudego;
    }

    public void setLongitudego(double longitudego) {
        this.longitudego = longitudego;
    }

    public double getLatitudecome() {
        return latitudecome;
    }

    public void setLatitudecome(double latitudecome) {
        this.latitudecome = latitudecome;
    }

    public double getLongitudecome() {
        return longitudecome;
    }

    public void setLongitudecome(double longitudecome) {
        this.longitudecome = longitudecome;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isFisnish() {
        return fisnish;
    }

    public void setFisnish(boolean fisnish) {
        this.fisnish = fisnish;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(keyOrder);
        parcel.writeString(keyclient);
        parcel.writeString(keydriver);
        parcel.writeString(placenamego);
        parcel.writeString(placenamecome);
        parcel.writeString(date);
        parcel.writeDouble(latitudego);
        parcel.writeDouble(longitudego);
        parcel.writeDouble(latitudecome);
        parcel.writeDouble(longitudecome);
        parcel.writeDouble(price);
        parcel.writeDouble(rate);
        parcel.writeDouble(distance);
        parcel.writeByte((byte) (status ? 1 : 0));
        parcel.writeByte((byte) (fisnish ? 1 : 0));
    }
}
