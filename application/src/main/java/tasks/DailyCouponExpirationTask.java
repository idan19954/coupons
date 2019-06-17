package tasks;
import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;
import dao.coupon.CouponDao;
import dao.coupon.CouponDaoImpl;
import dao.customer.CustomerDao;
import dao.customer.CustomerDaoImpl;
import model.Coupon;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DailyCouponExpirationTask implements Runnable {

    private CompanyDao companyDao = new CompanyDaoImpl();
    private CustomerDao customerDao = new CustomerDaoImpl();
    private CouponDao couponDao = new CouponDaoImpl();
    private boolean quit ;

    Thread currentThread = Thread.currentThread();

    public DailyCouponExpirationTask() {

    }

    public Thread getCurrentThread(){
        return currentThread;
    }

    public void setCurrentThread(Thread currentThread) {
        this.currentThread = currentThread;
    }




    @Override
    public void run() {
        while (quit== false&&Thread.currentThread().isInterrupted()){
            try {
                removeExpiredCoupons();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000*60*60*24);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void stopTask() {
        quit = true;
        Thread.currentThread().interrupt();

    }

    public void removeExpiredCoupons() throws SQLException {
        java.sql.Date today = new java.sql.Date( new java.util.Date().getTime() );
        List<Coupon> allCoupons = couponDao.getAll();
        List<Coupon> oldCoupons = new ArrayList<>();
        for (Coupon coupon:allCoupons) {
            if(coupon.getEndDate().before(today)){
                oldCoupons.add(coupon);
            }

        }
        for (Coupon coupon:oldCoupons) {
            couponDao.delete(coupon.getId());


        }
    }
}
