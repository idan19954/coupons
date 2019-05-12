package dao.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import lib.db.SQLConnectionPool;
import lib.model.Company;
import lib.model.Coupon;
import lib.model.utils.CouponType;
import lib.exceptions.UniqueValueException;
import lib.exceptions.SqlServerException;

public class CompanyDaoImpl implements CompanyDao {
    private SQLConnectionPool pool = SQLConnectionPool.getInstance();
    private Connection connection = pool.getConnection();

    @Override
    public void create( Company company ) throws UniqueValueException {
        try {
            Statement st = connection.createStatement();
            String query = String.format( "INSERT INTO companies VALUES ('%s', '%s', '%s')", company.getName(), company.getPassword(), company.getEmail() );
            int result = st.executeUpdate( query );

            if ( result == 0 ) {
                throw new UniqueValueException( "Company name already exists: ", company.getName() );
            } else {
                System.out.println( "Company created successfully" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            pool.returnConnection( connection );
        }
    }

    @Override
    public void delete( Company company ) throws SQLException {
        try {
            connection.setAutoCommit( false );

            PreparedStatement st = connection.prepareStatement( "DELETE FROM companies WHERE id = ?" );
            st.setLong( 1, company.getId() );

            PreparedStatement st2 = connection.prepareStatement( "DELETE FROM comp_coupon WHERE compid = ?" );
            st2.setLong( 1, company.getId() );

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

    @Override
    public void update( Company company ) throws SqlServerException {
        try {
            Statement st = connection.createStatement();
            String sql = String.format( "UPDATE companies SET email = %s, password = %s WHERE id = %d", company.getEmail(), company.getPassword(), company.getId() );
            int result = st.executeUpdate( sql );

            if ( result == 0 ) {
                throw new SqlServerException( "Error updating company", "updateCompany()" );
            }
        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( connection );
        }
    }

    @Override
    public Company getOne( long id ) {
        Company company = null;

        try {
            PreparedStatement st = connection.prepareStatement( "SELECT * FROM companies WHERE id = ?" );
            st.setLong( 1, id );
            ResultSet rs = st.executeQuery();

            if ( rs.next() ) {
                String name = rs.getString( 2 );
                String password = rs.getString( 3 );
                String email = rs.getString( 4 );
                company = new Company( id, name, password, email );
            }
        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( connection );
        }

        return company;
    }

    @Override
    public List<Company> getAll() {
        ArrayList<Company> companies = new ArrayList<>();

        Connection con = pool.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery( "select * from companies" );

            while ( rs.next() ) {
                long id = rs.getLong( 1 );
                String name = rs.getString( 2 );
                String password = rs.getString( 3 );
                String email = rs.getString( 4 );
                companies.add( new Company( id, name, password, email ) );
            }

        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( con );
        }

        return companies;
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons( long companyId ) {

        ArrayList<Coupon> coupons = new ArrayList<>();

        Connection con = pool.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery( "select * from coupons join company_coupon "
                    + "on coupons.couponid = company_coupon.couponid "
                    + "where company_coupon.companyid = " + companyId );
            while ( rs.next() ) {
                long coupondId = rs.getLong( 1 );
                String title = rs.getString( 2 );
                Date startDate = rs.getDate( 3 );
                Date endDate = rs.getDate( 4 );
                CouponType type = CouponType.valueOf( rs.getString( 10 ) );
                coupons.add( new Coupon( coupondId, title, startDate, endDate, 0, 0, type ) );
            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            pool.returnConnection( con );
        }

        return coupons;
    }

    @Override
    public long login( String companyName, String password ) {
        Connection con = pool.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery( "select id, password, companyName from companies "
                    + "where companyName like '" + companyName + "'" );
            if ( rs.next() ) {
                String pass = rs.getString( "password" );
                if ( password.equals( pass ) ) {
                    return rs.getLong( "id" ); // name and password match!
                } else {
                    return -1; // password incorrect
                }
            } else { // no such companyName
                return -1;
            }


        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }
}