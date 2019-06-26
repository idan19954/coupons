package model;

import lombok.Data;

import java.util.*;

@Data
public class Company {
    private int id;
    private String name;
    private String password;
    private String email;

    private List<Coupon> coupons;

    public Company( int id, String name, String password, String email ) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "id: "+ id + "name: "+ name+ "email: "+email + "list of coupons: " +coupons;
    }
}
