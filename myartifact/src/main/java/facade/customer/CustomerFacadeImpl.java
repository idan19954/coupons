package facade.customer;

import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;
import dao.coupon.CouponDao;
import dao.coupon.CouponDaoImpl;
import dao.customer.CustomerDao;
import dao.customer.CustomerDaoImpl;
import facade.CouponClientFacade;
import lib.exceptions.AuthenticationFailedException;
import lib.exceptions.SqlServerException;
import model.Coupon;
import model.Customer;
import model.utils.CouponType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {
    private CompanyDao companyDao = new CompanyDaoImpl();
    private CustomerDao customerDao = new CustomerDaoImpl();
    private CouponDao couponDao = new CouponDaoImpl();

    @Override
    public CouponClientFacade login( String name, String password ) throws AuthenticationFailedException {
        if ( customerDao.login( name, password ) ) {
            return this;
        }

        throw new AuthenticationFailedException( "User and password are wrong" );
    }

    @Override
    public void purchaseCoupon( Coupon coupon, Customer customer ) throws SqlServerException, SQLException {
        List<Coupon> coupons = customerDao.fetchCustomerCoupons( customer.getCustomerId() );
        java.sql.Date today = new java.sql.Date( new java.util.Date().getTime() );

        if ( !coupons.contains( coupon ) && coupon.getAmount() > 0 && coupon.getEndDate().before( today ) ) {
            customerDao.addCouponToCustomer( customer.getCustomerId(), coupon.getId() );
            coupon.setAmount( coupon.getAmount() - 1 );
            couponDao.update( coupon );

        }
    }

    @Override
    public List<Coupon> getAllCustomerCoupons( Customer customer ) {

        return customerDao.fetchCustomerCoupons( customer.getCustomerId() );
    }

    @Override
    public List<Coupon> getAllPurchasedCouponsByType( CouponType type, Customer customer ) {
        List<Coupon> allCouponsOfCustomer = customerDao.fetchCustomerCoupons( customer.getCustomerId() );
        List<Coupon> allCouponsByType = new ArrayList<>();
        for ( Coupon coupon : allCouponsOfCustomer ) {
            if ( coupon.getType().equals( type ) ) {
                allCouponsByType.add( coupon );
            }

        }
        return allCouponsByType;
    }


    @Override
    public List<Coupon> getCustomerCouponsByPrice( double price, Customer customer ) throws SqlServerException {
        List<Coupon> allCouponsOfCustomer = customerDao.fetchCustomerCoupons( customer.getCustomerId() );
        List<Coupon> allCouponsByPrice = new ArrayList<>();
        for ( Coupon coupon : allCouponsOfCustomer ) {
            if ( coupon.getPrice() < price ) {
                allCouponsByPrice.add( coupon );
            }

        }

        return allCouponsByPrice;
    }
}
