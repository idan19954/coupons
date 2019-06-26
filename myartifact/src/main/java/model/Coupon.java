package model;

import lombok.Data;
import model.utils.CouponType;

import java.sql.Date;

@Data
public class Coupon {
    private int id;
    private String title, message, image;
    private Date startDate, endDate;
    private int amount;
    private CouponType type;
    private double price;

    public Coupon() {

    }

    public Coupon( int id, String title, Date startDate, Date endDate, int amount, CouponType type, String message, double price, String image ) {
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

    public void setType( String type ) {
        this.type = CouponType.valueOf( type );
    }
}