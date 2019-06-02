package model;

import model.utils.ICouponable;

import java.util.List;

public class Customer  implements ICouponable {
    private List<Coupon> coupons;
    private long id;
    private String name;
    private String password;

    public Customer(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public Customer(long id,String name,String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coupon> getCoupons() {
        return this.coupons;
    }

    public void setCoupons( List<Coupon> coupons ) {
        this.coupons = coupons;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return this.coupons;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "coupons=" + coupons +
                '}';
    }
}