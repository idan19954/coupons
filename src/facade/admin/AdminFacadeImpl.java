package facade.admin;

import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;
import dao.coupon.CouponDao;
import dao.coupon.CouponDaoImpl;
import dao.customer.CustomerDao;
import dao.customer.CustomerDaoImpl;
import facade.CouponClientFacade;
import lib.UserType;
import lib.exceptions.AuthenticationFailedException;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Company;
import model.Customer;

import java.util.List;

public class AdminFacadeImpl implements AdminFacade {
    CompanyDao companyDao = new CompanyDaoImpl();
    CustomerDao customerDao = new CustomerDaoImpl();
    CouponDao couponDao = new CouponDaoImpl();

    @Override
    public CouponClientFacade login( String name, String password, UserType userType ) throws AuthenticationFailedException {
        if ( name.equals( "admin" ) && password.equals( "1234" ) ) {
            return this;
        }

        throw new AuthenticationFailedException( "User and password are wrong" );
    }

    @Override
    public int addNewCompany( Company company ) throws UniqueValueException {
        return 0;
    }

    @Override
    public void deleteCompany( int id ) throws SqlServerException {

    }

    @Override
    public void updateCompany( int id ) throws SqlServerException {

    }

    @Override
    public List<Company> getAllCompanies() {
        return null;
    }

    @Override
    public Company getOneCompany( int id ) throws SqlServerException {
        return null;
    }

    @Override
    public int addNewCustomer( Customer customer ) throws UniqueValueException {
        return 0;
    }

    @Override
    public boolean deleteCustomer( int id ) throws SqlServerException {
        return false;
    }

    @Override
    public boolean updateCustomer( Customer customer ) throws SqlServerException {
        return false;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getOneCustomer( int id ) throws SqlServerException {
        return null;
    }
}