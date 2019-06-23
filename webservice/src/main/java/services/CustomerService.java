package services;


import facade.admin.AdminFacadeImpl;
import model.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path( "/customer" )
public class CustomerService {

    @GET
    @Path( "/{customerId}" )
    public Response getCustomer( @PathParam( "customerId" ) int customerId ) throws SQLException {
        AdminFacadeImpl adminFacade = new AdminFacadeImpl();
        Customer customer = adminFacade.getOneCustomer( customerId );

        System.out.println( customerId );

        return null;
    }
}