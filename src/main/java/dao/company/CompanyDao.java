package dao.company;

import dao.Dao;
import model.Company;
import model.Coupon;

import java.sql.SQLException;
import java.util.List;


public interface CompanyDao extends Dao<Company> {
    List<Coupon> getCompanyCoupons( long companyId ) throws SQLException;

    boolean login( String companyName, String password );
}