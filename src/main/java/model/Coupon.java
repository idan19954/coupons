package model;

import model.utils.CouponType;

import java.sql.Date;

public class Coupon {
    private long id;
    private String title, message, image;
    private Date startDate, endDate;
    private int amount;
    private CouponType type;
    private double price;

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate( Date startDate ) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate( Date endDate ) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount( int amount ) {
        this.amount = amount;
    }

    public CouponType getType() {
        return type;
    }

    public void setType( CouponType type ) {
        this.type = type;
    }

    public void setType( String type ) {
        this.type = CouponType.valueOf( type );
    }

    public double getPrice() {
        return price;
    }

    public void setPrice( double price ) {
        this.price = price;
    }

    public Coupon() {

    }

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