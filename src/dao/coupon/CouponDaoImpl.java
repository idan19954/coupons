package dao.coupon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import lib.db.SQLConnectionPool;
import model.Coupon;
import model.utils.CouponType;

public class CouponDaoImpl implements CouponDao {
    private SQLConnectionPool pool = SQLConnectionPool.getInstance();
    private Connection connection = pool.getConnection();

    @Override
    public ArrayList<CouponType> getAllCouponsByType() {
        return null;
    }

    @Override
    public List<Coupon> getAll() {
        List<Coupon> coupons = new ArrayList<>();

        try {
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery( "SELECT * FROM coupons" );

            while ( rs.next() ) {
                Coupon coupon = new Coupon();

                coupon.setId( rs.getLong( "id" ) );
                coupon.setTitle( rs.getString( "title" ) );
                coupon.setMessage( rs.getString( "message" ) );
                coupon.setImage( rs.getString( "image" ) );
                coupon.setStartDate( rs.getDate( "start_date" ) );
                coupon.setEndDate( rs.getDate( "end_date" ) );
                coupon.setType( ( rs.getString( "coupon_type" ) ) );
                coupon.setAmount( rs.getInt( "amount" ) );
                coupon.setPrice( rs.getDouble( "price" ) );

                coupons.add( coupon );
            }
        } catch ( SQLException e ) {

        } finally {
            pool.returnConnection( this.connection );
        }

        return coupons;
    }

    @Override
    public Coupon getOne( long id ) {
        return null;
    }

    @Override
    public int create( Coupon coupon ) {
        return -1;
    }

    @Override
    public void update( Coupon coupon ) {

    }

    @Override
    public void delete( int id ) {

    }
}