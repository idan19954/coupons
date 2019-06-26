package services;

import facade.admin.AdminFacadeImpl;
import facade.customer.CustomerFacadeImpl;
import lib.exceptions.UniqueValueException;
import model.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "customer" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class CustomerServiceImpl implements CustomerService {
    private AdminFacadeImpl adminFacade;
    private CustomerFacadeImpl customerFacade;

    public CustomerServiceImpl() {
        this.adminFacade = new AdminFacadeImpl();
        this.customerFacade = new CustomerFacadeImpl();
    }

    @Override
    @GET
    @Path( "/{customerId}" )
    public Response getCustomer( @PathParam( "customerId" ) int customerId ) {
        try {
            Customer customer = adminFacade.getOneCustomer( customerId );

            return Response.status( Response.Status.OK ).entity( customer ).build();
        } catch ( Exception e ) {
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }
    }

    @Override
    @POST
    public Response createNewCustomer( Customer customer ) {
        try {
            int customerId = this.adminFacade.addNewCustomer( customer );

            return Response.status( Response.Status.CREATED ).entity( customerId ).build();
        } catch ( UniqueValueException e ) {

            return Response.status( Response.Status.UNSUPPORTED_MEDIA_TYPE ).entity( "{ error: Already exists }" ).build();
        }
    }
}