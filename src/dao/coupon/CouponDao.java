package dao.coupon;

import java.util.ArrayList;

import dao.Dao;
import model.Coupon;
import model.utils.CouponType;


public interface CouponDao extends Dao<Coupon> {
    ArrayList<CouponType> getAllCouponsByType();
}
