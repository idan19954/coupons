package facade.company;

import dao.company.CompanyDao;
import dao.company.CompanyDaoImpl;
import facade.CouponClientFacade;
import lib.UserType;

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
}
