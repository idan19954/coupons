package dao.coupon;

import java.util.List;

import dao.Dao;
import model.Coupon;
import model.utils.CouponType;

public interface CouponDao extends Dao<Coupon> {
    List<Coupon> getAllCouponsByType( CouponType type );
}
