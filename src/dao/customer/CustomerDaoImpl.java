package dao.customer;


import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getOne( long id ) {
        return null;
    }

    @Override
    public int create( Customer customer ) throws UniqueValueException {
        return 0;
    }

    @Override
    public void update( Customer customer ) throws SqlServerException {

    }

    @Override
    public void delete( int id ) throws SQLException {

    }



    @Override
    public boolean login( String customerName, String password ) {
        return false;
    }
}
