package facade;

import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;

public class CompanyFacade implements CouponClientFacade {
    CompanyDao companyDao = new CompanyDaoImpl();

    public CompanyFacade() {

    }

    @Override
    public CouponClientFacade login( String name, String password, UserType userType ) {
        return null;
    }

    public void addNewCoupon() {

    }
}
