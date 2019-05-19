import facade.AdminFacade;
import facade.ClientCouponFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;
import lib.db.SQLConnectionPool;

public class CouponSystem {
    private CouponSystem() {

    }

    public static CouponSystem getInstance() {
        return new CouponSystem();
    }

    public ClientCouponFacade login( String name, String password, UserType userType ) {
        switch ( userType ) {
            case ADMIN:
                return new AdminFacade();

            case CUSTOMER:
                return new CustomerFacade();

            case COMPANY:
                return new CompanyFacade();

            default:
                return null;
        }
    }

    public void shutdown() {
        // Stop daily task
        SQLConnectionPool sqlConnectionPool = SQLConnectionPool.getInstance();
        sqlConnectionPool.closeAllConnections();
    }
}
