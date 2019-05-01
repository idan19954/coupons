package daoDbdao;

import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.SqlServerException;
import exceptions.UniqueValueException;
import javaBeans.Company;
import javaBeans.Coupon;


public interface CompanyDao {
    void createCompany( Company company ) throws UniqueValueException;

    void removeCompany( Company company ) throws SQLException;

    void updateCompany( Company company ) throws SqlServerException;

    Company getCompany( long id );

    ArrayList<Company> getAllCompanies();

    ArrayList<Coupon> getCompanyCoupons( long companyId );

    long login( String companyName, String password );
}