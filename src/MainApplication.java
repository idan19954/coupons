public class MainApplication {
    public static void main( String[] args ) {
        CouponSystem couponSystem = CouponSystem.getInstance();
        couponSystem.login( "", "", UserType.ADMIN );
    }
}