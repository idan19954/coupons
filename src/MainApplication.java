import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;
import facade.UserType;

import java.util.Scanner;

public class MainApplication {
    public static void main( String[] args ) {
        CouponSystem couponSystem = CouponSystem.getInstance();
        Scanner scanner = new Scanner( System.in );

        System.out.println( "------------------------" );
        System.out.println( "Enter the type of user you wish to proceed with:" );
        System.out.println( "Type '1' for 'ADMIN'" );
        System.out.println( "Type '2' for 'CUSTOMER'" );
        System.out.println( "Type '3' for 'COMPANY'" );
        System.out.println( "------------------------" );

        int userType = scanner.nextInt();

        if ( userType == 1 ) {
            AdminFacade facade = (AdminFacade)( couponSystem.login( "ergtretgh", "1234", UserType.ADMIN ));
            facade.something();


        } else if ( userType == 2 ) {
            CustomerFacade facade = (CustomerFacade) ( couponSystem.login( "omersCompany", "mypassword", UserType.CUSTOMER ));
        } else if ( userType == 3 ) {
            CompanyFacade facade = (CompanyFacade)( couponSystem.login( "omersCompany", "mypassword", UserType.COMPANY ));
        }
    }
}