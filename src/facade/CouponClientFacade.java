package facade;

import lib.exceptions.AuthenticationFailedException;

public interface CouponClientFacade {
    CouponClientFacade login( String name, String password, UserType userType ) throws AuthenticationFailedException;
}
