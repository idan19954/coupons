package services;

import model.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface CustomerService {
    @GET
    @Path( "/{customerId}" )
    Response getCustomer( @PathParam( "customerId" ) int customerId );

    @POST
    Response createNewCustomer( Customer customer );
}
