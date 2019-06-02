package dao.customer;

import dao.Dao;
import model.Customer;

public interface CustomerDao extends Dao<Customer> {
    boolean login( String customerName, String password );
}
