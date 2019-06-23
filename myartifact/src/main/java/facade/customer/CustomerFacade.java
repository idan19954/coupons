package facade.customer;

import facade.CouponClientFacade;
import lib.exceptions.SqlServerException;
import model.Coupon;
import model.Customer;
import model.utils.CouponType;

import java.sql.SQLException;
import java.util.List;

public interface CustomerFacade extends CouponClientFacade {
    void purchaseCoupon( Coupon coupon, Customer customer ) throws SqlServerException, SQLException;

    List<Coupon> getAllCustomerCoupons( Customer customer ) throws SqlServerException;

    List<Coupon> getCustomerCouponsByPrice( double price, Customer customer ) throws SqlServerException;

    List<Coupon> getAllPurchasedCouponsByType( CouponType type, Customer customer );
}
