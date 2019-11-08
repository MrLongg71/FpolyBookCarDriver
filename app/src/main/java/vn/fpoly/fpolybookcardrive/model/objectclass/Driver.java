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
    private boolean statust;
    private boolean working;


    public String getKeydriver() {
        return keydriver;
    }

    public void setKeydriver(String keydriver) {
        this.keydriver = keydriver;
    }

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

    public boolean isStatust() {
        return statust;
    }

    public void setStatust(boolean statust) {
        this.statust = statust;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public Driver() {
    }

    public Driver(String keydriver, String email, String name, String phone, String licenseplate, double latitude, double longitude, int rate, boolean statust, boolean working) {
        this.keydriver = keydriver;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.licenseplate = licenseplate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rate = rate;
        this.statust = statust;
        this.working = working;
    }
}
