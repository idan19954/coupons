package facade.company;

import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;
import dao.coupon.CouponDao;
import dao.coupon.CouponDaoImpl;
import facade.CouponClientFacade;
import lib.exceptions.AuthenticationFailedException;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Company;
import model.Coupon;
import model.utils.CouponType;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyFacadeImpl implements CompanyFacade {
    CompanyDao companyDao = new CompanyDaoImpl();
    CouponDao couponDao = new CouponDaoImpl();

    private Company company;

    public CompanyFacadeImpl( Company company ) {
        this.company = company;


    }

    public CompanyFacadeImpl() {

    }

    @Override
    public CouponClientFacade login( String name, String password ) throws AuthenticationFailedException {
        if ( companyDao.login( name, password ) ) {
            return this;
        }
        throw new AuthenticationFailedException( "User and password are wrong" );
    }


    @Override
    public Coupon addNewCoupon( Coupon coupon ) throws UniqueValueException, SQLException {
        couponDao.create( coupon );
        companyDao.addCouponToCompany( company.getId(), coupon.getId() );
        return coupon;
    }

    @Override
    public void deleteCoupon( int id ) throws SQLException {
        couponDao.delete( id );
    }

    @Override
    public void updateCoupon( Date endDate, double price, Coupon coupon ) throws SqlServerException {
        java.sql.Date today = new java.sql.Date( new java.util.Date().getTime() );
        if ( endDate.after( today ) && price > 0 ) {
            coupon.setEndDate( endDate );
            coupon.setPrice( price );

            couponDao.update( coupon );

        }

    }

    @Override
    public Company getMyCompanyDetails() {
        return company;
    }

    @Override
    public List<Coupon> getCompanyCoupons( int id ) throws SQLException {
        List<Coupon> getAllCompanyCoupons = companyDao.getCompanyCoupons( company.getId() );

        return getAllCompanyCoupons;
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByType( CouponType type ) throws SQLException {
        List<Coupon> allCouponsOfCompany = companyDao.getCompanyCoupons( company.getId() );
        List<Coupon> allCouponsByType = new ArrayList<>();
        for ( Coupon coupon : allCouponsOfCompany ) {
            if ( coupon.getType().equals( type ) ) {
                allCouponsByType.add( coupon );
            }

        }
        return allCouponsByType;
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByPrice( double price ) throws SQLException {
        List<Coupon> allCouponsOfCompany = companyDao.getCompanyCoupons( company.getId() );
        List<Coupon> allCouponsByPrice = new ArrayList<>();
        for ( Coupon coupon : allCouponsOfCompany ) {
            if ( coupon.getPrice() < price ) {
                allCouponsByPrice.add( coupon );
            }
        }
        return allCouponsByPrice;
    }

    @Override
    public List<Coupon> getAllCompanyCouponsBeforeDate( Date endDate ) throws SQLException {
        List<Coupon> allCouponsOfCompany = companyDao.getCompanyCoupons( company.getId() );
        List<Coupon> allCouponsByEndDate = new ArrayList<>();
        for ( Coupon coupon : allCouponsOfCompany ) {
            if ( coupon.getEndDate().before( endDate ) ) {
                allCouponsByEndDate.add( coupon );
            }

        }
        return allCouponsByEndDate;
    }
}

