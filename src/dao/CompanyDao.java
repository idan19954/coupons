package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import lib.model.Company;
import lib.model.Coupon;


public interface CompanyDao {
    void createCompany( Company company ) throws UniqueValueException;

    void removeCompany( Company company ) throws SQLException;

    void updateCompany( Company company ) throws SqlServerException;

    Company getCompany( long id );

    ArrayList<Company> getAllCompanies();

    ArrayList<Coupon> getCompanyCoupons( long companyId );

    long login( String companyName, String password );
}