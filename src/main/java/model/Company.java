package model;

import model.utils.ICouponable;

import java.util.*;

public class Company implements ICouponable {
    private long id;
    private String name;
    private String password;
    private String email;

    private List<Coupon> coupons;

    public Company( long id, String name, String password, String email ) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCoupons(List<Coupon> coupons ) {
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