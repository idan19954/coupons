package facade.customer;

import facade.CouponClientFacade;
import lib.exceptions.SqlServerException;
import model.Coupon;
import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerFacade extends CouponClientFacade {
    void purchaseCoupon( Coupon coupon, Customer customer ) throws SqlServerException, SQLException;

    List<Coupon> getAllCustomerCoupons( Customer customer ) throws SqlServerException;

    List<Coupon> getCustomerCouponsByType( Customer customer ) throws SqlServerException;

    List<Coupon> getCustomerCouponsByDate( Customer customer ) throws SqlServerException;
}
