package facade.company;

import facade.CouponClientFacade;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Coupon;

public interface CompanyFacade extends CouponClientFacade {

    Coupon addNewCoupon( Coupon coupon ) throws UniqueValueException;

    void deleteCoupon( int id ) throws SqlServerException;

    boolean updateCoupon( Coupon coupon ) throws SqlServerException;
}
