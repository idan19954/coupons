package facade.company;

import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;
import dao.coupon.CouponDao;
import dao.coupon.CouponDaoImpl;
import facade.CouponClientFacade;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Coupon;

public class CompanyFacadeImpl implements CompanyFacade {
    CompanyDao companyDao = new CompanyDaoImpl();


    CouponDao  couponDao = new CouponDaoImpl();
    public CompanyFacadeImpl() {

    }

    @Override
    public CouponClientFacade login( String name, String password ) {
        return null;
    }


    @Override
    public Coupon addNewCoupon(Coupon coupon) throws UniqueValueException {


        return null;
    }

    @Override
    public void deleteCoupon(int id) throws SqlServerException {

    }

    @Override
    public boolean updateCoupon(Coupon coupon) throws SqlServerException {
        return false;
    }
}
