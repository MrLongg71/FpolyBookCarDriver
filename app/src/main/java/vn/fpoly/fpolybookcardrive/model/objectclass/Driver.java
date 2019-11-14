package vn.fpoly.fpolybookcardrive.model.objectclass;

public class Driver {
    private String keydriver;
    private String email;
    private String name;
    private String phone;
    private String licenseplate;
    private double latitude;
    private double longitude;
    private int rate;
    private int distance;
    private int time;
    private String token;
    private boolean status;
    private boolean working;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public Driver() {
    }

    public String getKeydriver() {
        return keydriver;
    }

    public void setKeydriver(String keydriver) {
        this.keydriver = keydriver;
    }

    public Driver(String keydriver, String email, String name, String phone, String licenseplate, double latitude, double longitude, int rate, int distance, int time, String token, boolean status, boolean working) {
        this.keydriver = keydriver;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.licenseplate = licenseplate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rate = rate;
        this.distance = distance;
        this.time = time;
        this.token = token;
        this.status = status;
        this.working = working;
    }
}
