package facade.admin;

import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;
import dao.coupon.CouponDao;
import dao.coupon.CouponDaoImpl;
import dao.customer.CustomerDao;
import dao.customer.CustomerDaoImpl;
import facade.CouponClientFacade;
import lib.exceptions.AuthenticationFailedException;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Company;
import model.Coupon;
import model.Customer;

import java.sql.SQLException;
import java.util.List;

public class AdminFacadeImpl implements AdminFacade {
    private CompanyDao companyDao = new CompanyDaoImpl();
    private CustomerDao customerDao = new CustomerDaoImpl();
    private CouponDao couponDao = new CouponDaoImpl();

    public CouponClientFacade login( String name, String password ) throws AuthenticationFailedException {
        if ( name.equals( "admin" ) && password.equals( "1234" ) ) {
            return this;
        }

        throw new AuthenticationFailedException( "User and password are wrong" );
    }

    @Override
    public void addNewCompany( Company company ) throws UniqueValueException {
        try {
            companyDao.create( company );
        } catch ( UniqueValueException e ) {
            throw new UniqueValueException( "Company name already exists: ", company.getName() );
        }
    }

    @Override
    public void deleteCompany( Company company ) throws SQLException {
        List<Coupon> coupons = companyDao.getCompanyCoupons( company.getId() );

        for ( Coupon coupon : coupons ) {
            couponDao.delete( coupon.getId() );
        }

        companyDao.delete( company.getId() );
    }

    @Override
    public void updateCompany( Company company ) throws SQLException, SqlServerException {
        companyDao.update( company );
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        return companyDao.getAll();
    }

    @Override
    public Company getOneCompany( int id ) throws SQLException {
        return companyDao.getOne( id );
    }

    @Override
    public void addNewCustomer( Customer customer ) throws UniqueValueException {
        customerDao.create( customer );
    }

    @Override
    public void deleteCustomer( int id ) throws SQLException {
        customerDao.delete( id );
    }

    @Override
    public void updateCustomer( Customer customer ) throws SQLException {
        try {
            customerDao.update( customer );
        } catch ( SqlServerException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return customerDao.getAll();
    }

    @Override
    public Customer getOneCustomer( int id ) throws SQLException {
        return customerDao.getOne( id );
    }
}