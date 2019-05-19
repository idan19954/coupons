package dao.coupon;

import dao.company.CompanyDaoImpl;
import lib.db.SQLConnectionPool;
import model.Coupon;
import model.utils.CouponType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

            return CompanyDaoImpl.fetchCoupons( rs );
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