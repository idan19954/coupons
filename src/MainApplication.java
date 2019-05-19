import facade.CouponClientFacade;
import facade.UserType;

public class MainApplication {
    public static void main( String[] args ) {
        CouponSystem couponSystem = CouponSystem.getInstance();
        CouponClientFacade facade = couponSystem.login( "", "", UserType.ADMIN );
    }
}