package daoDbdao;

import java.util.ArrayList;

import javaBeans.Coupon;
import javaBeans.CouponType;


public interface CouponDao {
	
	ArrayList<Coupon> coupons  = new ArrayList<Coupon>();

	
	ArrayList<Coupon> couponsByType  = new ArrayList<Coupon>();
	
	/*
	 * method that create a coupon .
	 */
	
	void createCoupon();
	
	/*
	 * method that remove a coupon .
	 */
	
	void removeCoupon();
	
	/*
	 * method that update details about the coupon .
	 */
	
	void updateCoupon();
	
	/*
	 * method that get info about the coupon .
	 */
	
	String getCoupon();
	
	/*
	 * method that get a collection of Coupon .
	 */
	
	ArrayList<Coupon> getAllCoupons();

	/*
	 * method that get a collection of CouponType  .
	 */
	ArrayList<CouponType> getAllCouponsByType();
}
