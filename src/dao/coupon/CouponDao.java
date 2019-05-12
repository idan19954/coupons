package dao.coupon;

import java.util.ArrayList;

import dao.Dao;
import lib.model.Coupon;
import lib.model.utils.CouponType;


public interface CouponDao extends Dao<Coupon> {
    ArrayList<CouponType> getAllCouponsByType();
}
