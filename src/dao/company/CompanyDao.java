package dao.company;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.Dao;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import lib.model.Company;
import lib.model.Coupon;


public interface CompanyDao extends Dao<Company> {
    ArrayList<Coupon> getCompanyCoupons( long companyId );

    long login( String companyName, String password );
}