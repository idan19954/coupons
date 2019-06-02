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
import model.Coupon;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminFacadeImpl implements AdminFacade {
    CompanyDao companyDao = new CompanyDaoImpl();
    CustomerDao customerDao = new CustomerDaoImpl();
    CouponDao couponDao = new CouponDaoImpl();


    public CouponClientFacade login(String name, String password, UserType userType) throws AuthenticationFailedException {
        if (name.equals("admin") && password.equals("1234")) {
            return this;
        }

        throw new AuthenticationFailedException("User and password are wrong");
    }

    @Override
    public void addNewCompany(Company company) throws UniqueValueException {
        try {
            companyDao.create(company);
        }
   catch (UniqueValueException e )
   {throw new UniqueValueException("COMPANY NAME ALREADY EXISTS:" + company.getName(),"admin facade:create company");
   }
    }

    @Override
    public void deleteCompany(Company company) throws SQLException {
        List<Coupon> coupons = companyDao.getCompanyCoupons(company.getId());
        for (Coupon coupon:coupons) {
            couponDao.delete(coupon.getId());

        }
                companyDao.delete(company.getId());
    }

    @Override
    public void updateCompany(Company company) throws SQLException, SqlServerException {
        companyDao.update(company );
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
      List<Company> companies = companyDao.getAll();


        return companies;
    }

    @Override
    public Company getOneCompany(long id) throws SQLException {
        Company c = companyDao.getOne(id);
        return c;
    }

    @Override
    public void addNewCustomer(Customer customer) throws UniqueValueException {
    customerDao.create(customer);
    }

    @Override
    public void deleteCustomer(long id) throws SQLException {
    customerDao.delete(id);
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        try {
            customerDao.update(customer);
        } catch (SqlServerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
      List<Customer> customers = customerDao.getAll();

        return customers;
    }

    @Override
    public Customer getOneCustomer(long id) throws SQLException {
      Customer customer = customerDao.getOne(id);
        return customer;
    }
}