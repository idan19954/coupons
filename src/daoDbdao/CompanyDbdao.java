package daoDbdao;

import java.sql.*;
import java.util.ArrayList;

import javaBeans.Company;
import javaBeans.Coupon;
import javaBeans.CouponType;
import db.ConnectionPool;
import exceptions.UniqueValueException;
import exceptions.SqlServerException;

public class CompanyDbdao implements CompanyDao {
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void createCompany( Company company ) throws UniqueValueException {
        Connection con = pool.getConnection();
        try {
            Statement st = con.createStatement();
            String sql = String.format( "insert into companies values('%s', '%s', '%s')",
                    company.getCompName(), company.getPassword(), company.getEmail() );

            int result = st.executeUpdate( sql );
            if ( result == 0 ) {
                throw new UniqueValueException( "Company name already exists", company.getCompName() );
            }
            System.out.println( "Company created successfully" );
        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            pool.returnConnection( con );
        }
    }

    @Override
    public void removeCompany( Company company ) throws SQLException {
        Connection con = pool.getConnection();
        try {

            // begin transaction
            con.setAutoCommit( false );

            // PreparedStatement if safer (no sql injection) and better when using transactions
            PreparedStatement st = con.prepareStatement( "delete from companies where id = ?" );
            st.setLong( 1, company.getID() );

            PreparedStatement st2 = con.prepareStatement( "delete from comp_coupon where compid = ?" );
            st2.setLong( 1, company.getID() );

            // try to execute the PreparedStatements
            int count1 = st.executeUpdate();
            int count2 = st2.executeUpdate();

            // if all the PreparedStatements where successful => commit changes (save)
            con.commit();

        } catch ( SQLException e ) {
            // if en exception was found => rollback!
            con.rollback();
            throw new SQLException( e.getMessage() );
        } finally {
            pool.returnConnection( con );
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void updateCompany( Company company ) throws SqlServerException {
        Connection con = pool.getConnection();
        try {
            Statement st = con.createStatement();
            String sql = String.format( "update companies set email = %s, password = %s where id = %d",
                    company.getEmail(), company.getPassword(), company.getID() );
            int res = st.executeUpdate( sql );
            if ( res == 0 ) {
                throw new SqlServerException( "Error updating company", "updateCompany()" );
            }
        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( con );
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Company getCompany( long id ) {
        Company c = null;

        Connection con = pool.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery( "select * from companies where id = " + id );
            if ( rs.next() ) {
                String name = rs.getString( 2 );
                String password = rs.getString( 3 );
                String email = rs.getString( 4 );
                c = new Company( id, name, password, email );
            }

        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( con );
        }

        return c;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ArrayList<Company> getAllCompanies() {

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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

	
}
