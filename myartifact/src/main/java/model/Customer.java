package model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@Data
public class Customer {
    private List<Coupon> coupons;
    private int customerId;
    private String name;
    private String password;

    @JsonCreator
    public Customer( @JsonProperty( "name" ) String name, @JsonProperty( "password" ) String password ) {
        this.name = name;
        this.password = password;
    }

    public Customer( int customerId, String name, String password ) {
        this.customerId = customerId;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" + "coupons=" + coupons + '}';
    }
}