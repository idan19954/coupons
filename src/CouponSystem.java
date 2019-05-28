import facade.*;
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
                    AdminFacade adminFacade = new AdminFacade();
                    return adminFacade.login( name, password, UserType.ADMIN );
                } catch ( Exception e ) {
                    e.printStackTrace();
                }

            case CUSTOMER:
                return new CustomerFacade();

            case COMPANY:
                try {
                    CompanyFacade companyFacade = new CompanyFacade();
                    return companyFacade.login( name, password, UserType.COMPANY );
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
