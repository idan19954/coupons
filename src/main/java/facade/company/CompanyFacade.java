package facade.company;

import facade.CouponClientFacade;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Company;
import model.Coupon;
import model.utils.CouponType;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface CompanyFacade extends CouponClientFacade {

    Coupon addNewCoupon( Coupon coupon ) throws UniqueValueException, SQLException;

    void deleteCoupon( int id ) throws SqlServerException, SQLException;

    void updateCoupon(Date endDate,double price,Coupon coupon) throws SqlServerException;

    Company getMyCompanyDetails();

    List<Coupon> getCompanyCoupons(int id) throws SQLException;

    List<Coupon> getAllCompanyCouponsByType(CouponType type) throws SQLException;

    List<Coupon> getAllCompanyCouponsByPrice(double price) throws SQLException;

    List<Coupon> getAllCompanyCouponsBeforeDate(Date endDate) throws SQLException;

}
