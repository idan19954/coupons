package facade.admin;

import facade.CouponClientFacade;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Company;
import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface AdminFacade extends CouponClientFacade {
    void addNewCompany( Company company ) throws UniqueValueException;

    void deleteCompany( int id ) throws SQLException;

    void updateCompany( Company company ) throws SQLException, SqlServerException;

    List<Company> getAllCompanies() throws SQLException;

    Company getOneCompany( int id ) throws SQLException;

    void addNewCustomer( Customer customer ) throws UniqueValueException;

    void deleteCustomer( int id ) throws SQLException;

    void updateCustomer( Customer customer ) throws SQLException;

    List<Customer> getAllCustomers() throws SQLException;

    Customer getOneCustomer( int id ) throws SQLException;
}
