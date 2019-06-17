package dao.customer;

import dao.Dao;
import model.Coupon;
import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao extends Dao<Customer> {
    boolean login( String customerName, String password );

    List<Coupon> fetchCustomerCoupons( int customerId );

    void addCouponToCustomer( int customerId, int id ) throws SQLException;
}
