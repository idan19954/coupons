package dao.coupon;

import dao.company.CompanyDaoImpl;
import lib.db.SQLConnectionPool;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Coupon;
import model.utils.CouponType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponDaoImpl implements CouponDao {
    private SQLConnectionPool pool = SQLConnectionPool.getInstance();
    private Connection connection = pool.getConnection();

    @Override
    public List<Coupon> getAllCouponsByType( CouponType type ) {
        List<Coupon> couponsByType = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement( "SELECT * FROM coupons WHERE type = ?" );
            st.setString( 1, type.toString() );
            ResultSet rs = st.executeQuery();
            return CompanyDaoImpl.fetchCoupons( rs );
        } catch ( SQLException e ) {
            e.printStackTrace();

        } finally {
            pool.returnConnection( this.connection );
        }

        return couponsByType;
    }

    @Override
    public List<Coupon> getAll() {
        List<Coupon> coupons = new ArrayList<>();

        try {
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery( "SELECT * FROM coupons" );

            return CompanyDaoImpl.fetchCoupons( rs );
        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( this.connection );
        }

        return coupons;
    }

    @Override
    public Coupon getOne( int id ) {
        Coupon coupon = null;

        try {
            PreparedStatement st = connection.prepareStatement( "SELECT * FROM coupons WHERE coupon_id = ?" );
            st.setLong( 1, id );
            ResultSet rs = st.executeQuery();

            if ( rs.next() ) {
                String title = rs.getString( 2 );
                Date startDate = Date.valueOf( rs.getString( 3 ) );
                Date endDate = Date.valueOf( rs.getString( 4 ) );
                int amount = Integer.parseInt( rs.getString( 5 ) );
                CouponType type = CouponType.valueOf( rs.getString( 6 ) );
                String message = rs.getString( 7 );
                double price = Double.parseDouble( rs.getString( 8 ) );
                String image = rs.getString( 9 );


                coupon = new Coupon( id, title, startDate, endDate, amount, type, message, price, image );
            }
        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( connection );
        }

        return coupon;
    }

    @Override
    public int create( Coupon coupon ) {
        try {
            Statement st = connection.createStatement();
            String query = String.format( "INSERT INTO coupons (title,start_Date end_Date,amount,type,message,price,image_path) VALUES ('%s', '%s', '%s','%s','%s','%s','%s','%')", coupon.getTitle(), coupon.getStartDate(), coupon.getEndDate(), coupon.getAmount(), coupon.getType(), coupon.getMessage(), coupon.getPrice(), coupon.getImage() );


            int result = st.executeUpdate( query, Statement.RETURN_GENERATED_KEYS );
            ResultSet generatedKeys = st.getGeneratedKeys();

            if ( result == 0 ) {
                throw new UniqueValueException( "Coupon title already exists: ", coupon.getTitle() );
            } else {
                System.out.println( "coupon created successfully" );
            }

            if ( generatedKeys.next() ) {
                return generatedKeys.getInt( 1 );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( UniqueValueException e ) {
            e.printStackTrace();
        } finally {
            pool.returnConnection( connection );
        }

        return -1;
    }

    @Override
    public void update( Coupon coupon ) throws SqlServerException {
        try {
            Statement st = connection.createStatement();
            String sql = String.format( "UPDATE coupons SET  end_Date = %s, amount = %s,price = %s = %s, WHERE id = %d", coupon.getEndDate(), coupon.getAmount(), coupon.getPrice(), coupon.getId() );
            int result = st.executeUpdate( sql );

            if ( result == 0 ) {
                throw new SqlServerException( "Error updating coupon", "updateCoupon()" );
            }
        } catch ( SQLException e ) {


        } finally {
            pool.returnConnection( connection );
        }
    }


    @Override
    public void delete( int id ) throws SQLException {
        Connection connection = pool.getConnection();
        try {
            connection.setAutoCommit( false );

            PreparedStatement st = connection.prepareStatement( "DELETE FROM coupons WHERE coupon_id = ?" );
            st.setLong( 1, id );

            PreparedStatement st2 = connection.prepareStatement( "DELETE FROM company_coupon WHERE coupon_id = ?" );
            st2.setLong( 1, id );

            PreparedStatement st3 = connection.prepareStatement( "DELETE FROM customer_coupon WHERE coupon_id = ?" );
            st3.setLong( 1, id );
            st.executeUpdate();
            st2.executeUpdate();
            st3.executeUpdate();
            connection.commit();
        } catch ( SQLException e ) {
            connection.rollback();
            throw new SQLException( e.getMessage() );
        } finally {
            pool.returnConnection( connection );
        }

    }
}