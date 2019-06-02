package dao.company;

import lib.db.SQLConnectionPool;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Company;
import model.Coupon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    private SQLConnectionPool pool = SQLConnectionPool.getInstance();
    private Connection connection = pool.getConnection();

    /**
     * @param company Company
     * @return int
     * @throws UniqueValueException
     */
    @Override
    public int create( Company company ) throws UniqueValueException {
        try {
            Statement st = connection.createStatement();
            String query = String.format( "INSERT INTO companies (name, password, email) VALUES ('%s', '%s', '%s')", company.getName(), company.getPassword(), company.getEmail() );

            int result = st.executeUpdate( query, Statement.RETURN_GENERATED_KEYS );
            ResultSet generatedKeys = st.getGeneratedKeys();

            if ( result == 0 ) {
                throw new UniqueValueException( "Company name already exists: ", company.getName() );
            } else {
                System.out.println( "Company created successfully" );
            }

            if ( generatedKeys.next() ) {
                return generatedKeys.getInt( 1 );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            pool.returnConnection( connection );
        }

        return -1;
    }

    @Override
    public void delete( long id ) throws SQLException {
        try {
            connection.setAutoCommit( false );

            PreparedStatement st = connection.prepareStatement( "DELETE FROM companies WHERE company_id = ?" );
            st.setLong( 1, id );

            PreparedStatement st2 = connection.prepareStatement( "DELETE FROM company_coupon WHERE company_id = ?" );
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
    public Company getOne( long id ) throws SQLException{
        Company company = null;

        try {
            PreparedStatement st = connection.prepareStatement( "SELECT * FROM companies WHERE company_id = ?" );
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
    public List<Company> getAll() throws SQLException{
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
    public List<Coupon> getCompanyCoupons( long companyId ) throws SQLException{
        List<Coupon> coupons = new ArrayList<>();
        Connection con = pool.getConnection();

        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                "SELECT * FROM coupons c " +
                "JOIN company_coupon cc on cc.coupon_id = c.coupon_id " +
                "WHERE cc.company_id = " + companyId
            );

            return fetchCoupons( rs );
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            pool.returnConnection( con );
        }

        return coupons;
    }

    public static List<Coupon> fetchCoupons( ResultSet rs ) throws SQLException {
        List<Coupon> coupons = new ArrayList<>();

        while ( rs.next() ) {
            Coupon coupon = new Coupon();

            coupon.setId( rs.getLong( "coupon_id" ) );
            coupon.setTitle( rs.getString( "title" ) );
            coupon.setMessage( rs.getString( "message" ) );
            coupon.setImage( rs.getString( "image_path" ) );
            coupon.setStartDate( rs.getDate( "start_date" ) );
            coupon.setEndDate( rs.getDate( "end_date" ) );
            coupon.setType( (rs.getString( "type" )) );
            coupon.setAmount( rs.getInt( "amount" ) );
            coupon.setPrice( rs.getDouble( "price" ) );

            coupons.add( coupon );
        }

        return coupons;
    }

    @Override
    public boolean login( String companyName, String password ) {
        return true;
    }
}