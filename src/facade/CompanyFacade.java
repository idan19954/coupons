package facade;

import dao.company.CompanyDao;

public class CompanyFacade implements CouponClientFacade {
    CompanyDao companyDao;

    public CompanyFacade() {

    }

    @Override
    public CouponClientFacade login( String name, String password, UserType userType ) {
        return null;
    }

    public void addNewCoupon() {

    }
}
