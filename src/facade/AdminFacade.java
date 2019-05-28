package facade;

import lib.exceptions.AuthenticationFailedException;

public class AdminFacade implements CouponClientFacade {
    @Override
    public CouponClientFacade login( String name, String password, UserType userType ) throws AuthenticationFailedException {
        if ( name.equals( "admin" ) && password.equals( "1234" ) ) {
            return this;
        }

        throw new AuthenticationFailedException( "User and password are wrong" );
    }

    public void something() {
        System.out.println("Hello");
    }
}