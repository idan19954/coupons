package model;

import java.util.List;
import model.utils.ICouponable;

public class Customer implements ICouponable {
    private List<Coupon> coupons;
    private int customerId;
    private String name;
    private String password;

    public Customer( int customerId, String name, String password ) {
        this.customerId = customerId;
        this.name = name;
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public void setName( String name ) {
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