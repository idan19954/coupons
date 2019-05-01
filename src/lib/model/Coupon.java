package lib.model;

import lib.model.utils.CouponType;

import java.sql.Date;

public class Coupon {
    private long id;
    private String title, message, image;
    private Date startDate, endDate;
    private int amount;
    private CouponType type;
    private double price;

    public Coupon( long id, String title, Date startDate, Date endDate, int amount, CouponType type, String message, double price, String image ) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.type = type;
        this.message = message;
        this.price = price;
        this.image = image;
    }
}