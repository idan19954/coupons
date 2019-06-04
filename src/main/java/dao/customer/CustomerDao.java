package dao.customer;

import dao.Dao;
import model.Coupon;
import model.Customer;

import java.util.List;

public interface CustomerDao extends Dao<Customer> {
    boolean login( String customerName, String password );

    List<Coupon> fetchCustomerCoupons( int id );
}
