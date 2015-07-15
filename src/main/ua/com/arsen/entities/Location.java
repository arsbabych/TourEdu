package ua.com.arsen.entities;

/**
 * Created by Thor on 19.02.2015.
 */
public class Location {
    private int id;
    private String country;
    private String city;
    private String address;
    private String hotelName;
    private int hotelStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(int hotelStatus) throws Exception {
        if (hotelStatus < 1 || hotelStatus > 5) {
            throw new Exception("Hotel status value should be between 1 and 5");
        }
        this.hotelStatus = hotelStatus;
    }
}
