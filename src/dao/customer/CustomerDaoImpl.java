package dao.customer;


import lib.db.SQLConnectionPool;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Company;
import model.Coupon;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private SQLConnectionPool pool = SQLConnectionPool.getInstance();
    private Connection connection = pool.getConnection();


    @Override
    public List<Customer> getAll() {
        ArrayList<Customer> customers = new ArrayList<>();

        Connection connection = pool.getConnection();
        try {
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery( "select * from customers" );

            while ( rs.next() ) {
                long id = rs.getLong( 1 );
                String name = rs.getString( 2 );
                String password = rs.getString( 3 );

                customers.add( new Customer( id,name,password) );
            }

        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( connection );
        }

        return customers;
    }




    @Override
    public Customer getOne( long id ) {

        Customer customer = null;
            Connection connection = pool.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement( "SELECT * FROM customers WHERE customer_id = ?" );
            st.setLong( 1, id );
            ResultSet rs = st.executeQuery();

            if ( rs.next() ) {
                String name = rs.getString( 2 );
                String password = rs.getString( 3 );

                customer = new Customer(id,name,password);
            }
        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( connection );
        }

        return customer;

    }

    @Override
    public int create( Customer customer ) throws UniqueValueException {

       Connection connection = pool.getConnection();
        try {
            Statement st = connection.createStatement();
            String query = String.format( "INSERT INTO customers (name, password ) VALUES ('%s', '%s')", customer.getName(), customer.getPassword());

            int result = st.executeUpdate( query, Statement.RETURN_GENERATED_KEYS );
            ResultSet generatedKeys = st.getGeneratedKeys();

            if ( result == 0 ) {
                throw new UniqueValueException( "Customer name already exists: ", customer.getName() );
            } else {
                System.out.println( "Customer created successfully" );
            }

            if ( generatedKeys.next() ) {
                return generatedKeys.getInt( 1 );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            pool.returnConnection( connection );
        }

        return 0;
    }

    @Override
    public void update( Customer customer ) throws SqlServerException {

        Connection connection = pool.getConnection();
        try {
            Statement st = connection.createStatement();
            String sql = String.format( "UPDATE customers SET password = %s WHERE id = %d",  customer.getPassword(), customer.getId() );
            int result = st.executeUpdate( sql );

            if ( result == 0 ) {
                throw new SqlServerException( "Error updating customer", "updateCustomer()" );
            }
        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( connection );
        }

    }

    @Override
    public void delete( long id ) throws SQLException {

       Connection connection = pool.getConnection();
        try {
            connection.setAutoCommit( false );

            PreparedStatement st = connection.prepareStatement( "DELETE FROM customers WHERE customer_id = ?" );
            st.setLong( 1, id );

            PreparedStatement st2 = connection.prepareStatement( "DELETE FROM customer_coupon WHERE customer_id = ?" );
            st2.setLong( 1, id );

            st.executeUpdate();
            st2.executeUpdate();

            connection.commit();
        } catch ( SQLException e ) {
            connection.rollback();
            throw new SQLException( e.getMessage() );
        } finally {
            pool.returnConnection( connection );
        }

    }
    public List<Coupon> getCustomerCoupons() {
        ArrayList<Coupon> customercoupons = new ArrayList<>();

        Connection connection = pool.getConnection();
        try {
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery( "select * from custoner_coupon WHERE customer_id = ?" );

            while ( rs.next() ) {
                long id = rs.getLong( 1 );
                String name = rs.getString( 2 );
                String password = rs.getString( 3 );

                customercoupons.add( new Coupon(title,) );
            }

        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( connection );
        }

        return customers;
    }


    @Override
    public boolean login( String customerName, String password ) {
        return false;
    }
}
