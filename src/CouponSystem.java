import facade.*;
import facade.admin.AdminFacadeImpl;
import facade.company.CompanyFacadeImpl;
import facade.customer.CustomerFacadeImpl;
import lib.UserType;
import lib.db.SQLConnectionPool;
import tasks.DailyCouponExpirationTask;

public class CouponSystem {
    private DailyCouponExpirationTask task;

    private CouponSystem() {
        this.task = new DailyCouponExpirationTask();
        this.task.run();
    }

    public static CouponSystem getInstance() {
        return new CouponSystem();
    }

    public CouponClientFacade login( String name, String password, UserType userType ) {
        switch ( userType ) {
            case ADMIN:
                try {
                    AdminFacadeImpl adminFacadeImpl = new AdminFacadeImpl();
                    return adminFacadeImpl.login( name, password, UserType.ADMIN );
                } catch ( Exception e ) {
                    e.printStackTrace();
                }

            case CUSTOMER:
                return new CustomerFacadeImpl();

            case COMPANY:
                try {
                    CompanyFacadeImpl companyFacadeImpl = new CompanyFacadeImpl();
                    return companyFacadeImpl.login( name, password, UserType.COMPANY );
                } catch ( Exception e ) {
                    e.printStackTrace();
                }

            default:
                return null;
        }
    }

    public void shutdown() {
        // Stop daily task
        task.stopTask();
        SQLConnectionPool sqlConnectionPool = SQLConnectionPool.getInstance();
        sqlConnectionPool.closeAllConnections();
    }
}
