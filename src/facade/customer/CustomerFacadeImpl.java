package facade.customer;

import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;
import dao.coupon.CouponDao;
import dao.coupon.CouponDaoImpl;
import dao.customer.CustomerDao;
import dao.customer.CustomerDaoImpl;
import facade.CouponClientFacade;
import lib.UserType;
import lib.exceptions.SqlServerException;
import model.Coupon;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {
    CompanyDao companyDao = new CompanyDaoImpl();
    CustomerDao customerDao = new CustomerDaoImpl();
    CouponDao couponDao = new CouponDaoImpl();


    @Override
    public CouponClientFacade login( String name, String password, UserType userType ) {
        return null;
    }

    @Override
    public void purchaseCoupon(Coupon coupon, Customer customer) throws SqlServerException, SQLException {
        List<Coupon>  coupons= couponDao.getAll();
        java.sql.Date today = new java.sql.Date(new java.util.Date().getTime());
        if(!coupons.contains(coupon) && coupon.getAmount() > && coupon.getEndDate().before(today)  )
    }

    @Override
    public List<Coupon> getAllcustomerCoupons(Customer customer) throws SqlServerException {
        return null;
    }

    @Override
    public List<Coupon> getCustomerCouponsByType(Customer customer) throws SqlServerException {
        return null;
    }

    @Override
    public List<Coupon> getCustomerCouponsByDate(Customer customer) throws SqlServerException {
        return null;
    }
}
