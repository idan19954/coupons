package model;

import model.utils.AuthUser;
import model.utils.ICouponable;

import java.util.*;

public class Company extends AuthUser implements ICouponable {
    private long id;
    private String email;

    private List<Coupon> coupons;

    public Company( long id, String name, String password, String email ) {
        super( name, password, id );
        this.id = id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setCoupons( List<Coupon> coupons ) {
        this.coupons = coupons;
    }

    public List<Coupon> getCoupons() {
        return this.coupons;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return this.coupons;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", coupons=" + coupons +
                '}';
    }
}