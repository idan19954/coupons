package dao.company;

import dao.Dao;
import model.Company;
import model.Coupon;

import java.sql.SQLException;
import java.util.List;


public interface CompanyDao extends Dao<Company> {
    List<Coupon> getCompanyCoupons( int companyId ) throws SQLException;

    boolean login( String companyName, String password );
    void  addCouponToCompany(int companyId ,int id) throws SQLException;
}