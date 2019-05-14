package dao.company;

import java.util.ArrayList;

import dao.Dao;
import model.Company;
import model.Coupon;


public interface CompanyDao extends Dao<Company> {
    ArrayList<Coupon> getCompanyCoupons( long companyId );

    long login( String companyName, String password );
}