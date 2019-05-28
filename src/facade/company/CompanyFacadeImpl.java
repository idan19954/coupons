package facade.company;

import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;
import facade.CouponClientFacade;
import lib.UserType;
import lib.exceptions.SqlServerException;
import lib.exceptions.UniqueValueException;
import model.Coupon;

public class CompanyFacadeImpl implements CompanyFacade {
    CompanyDao companyDao = new CompanyDaoImpl();

    public CompanyFacadeImpl() {

    }

    @Override
    public CouponClientFacade login( String name, String password, UserType userType ) {
        return null;
    }

    public void addNewCoupon() {

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
