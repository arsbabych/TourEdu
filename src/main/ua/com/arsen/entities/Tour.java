package ua.com.arsen.entities;

import com.google.gson.Gson;

import java.sql.Date;

/**
 * Created by Thor on 15.02.2015.
 */
public class Tour {
    private int id;
    private int locationId;
    private Date startDate;
    private int countDays;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getCountDays() {
        return countDays;
    }

    public void setCountDays(int countDays) {
        this.countDays = countDays;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        Gson gson = new Gson();

        return gson.toJson(this);
    }
}
