import dao.customer.CustomerDao;
import dao.customer.CustomerDaoImpl;
import facade.admin.AdminFacade;
import facade.admin.AdminFacadeImpl;
import facade.company.CompanyFacadeImpl;
import facade.customer.CustomerFacadeImpl;
import lib.UserType;

import java.util.Scanner;

public class MainApplication {
    public static void main( String[] args ) {
        /*
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
            AdminFacade facade = (AdminFacadeImpl) (couponSystem.login( "ergtretgh", "1234", UserType.ADMIN ));
        } else if ( userType == 2 ) {
            CustomerFacadeImpl facade = (CustomerFacadeImpl) (couponSystem.login( "agnes-bayer", "qt2hq", UserType.CUSTOMER ));

        } else if ( userType == 3 ) {
            CompanyFacadeImpl facade = (CompanyFacadeImpl) (couponSystem.login( "omersCompany", "mypassword", UserType.COMPANY ));
        }
        */
        CustomerDao customerDao = new CustomerDaoImpl();
        System.out.println( customerDao.fetchCustomerCoupons( 8965 ) );
    }
}