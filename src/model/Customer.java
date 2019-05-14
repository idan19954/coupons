package model;

import model.utils.AuthUser;
import model.utils.ICouponable;

import java.util.List;

public class Customer extends AuthUser implements ICouponable {
    private List<Coupon> coupons;

    public Customer( long id, String name, String password, List<Coupon> coupons ) {
        super( name, password, id );
        this.coupons = coupons;
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


