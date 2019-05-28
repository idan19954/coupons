package facade.admin;

import facade.CouponClientFacade;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Company;
import model.Customer;

import java.util.List;

public interface AdminFacade extends CouponClientFacade {
    int addNewCompany( Company company ) throws UniqueValueException;

    void deleteCompany( int id ) throws SqlServerException;

    void updateCompany( int id ) throws SqlServerException;

    List<Company> getAllCompanies();

    Company getOneCompany( int id ) throws SqlServerException;

    int addNewCustomer( Customer customer ) throws UniqueValueException;

    boolean deleteCustomer( int id ) throws SqlServerException;

    boolean updateCustomer( Customer customer ) throws SqlServerException;

    List<Customer> getAllCustomers();

    Customer getOneCustomer( int id ) throws SqlServerException;
}
