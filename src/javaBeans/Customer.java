package javaBeans;

import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Customer {

	private long id;
	private String custName;
	private String password;
	ArrayList<Coupon> couponsCust  = new ArrayList<Coupon>();
	


public Customer(long id, String custName, String password, ArrayList<Coupon> couponsCust) {
	super();
	this.id = id;
	this.custName = custName;
	this.password = password;
	this.couponsCust = couponsCust;
}



public String getCustName() {
	return custName;
}



public void setCustName(String custName) {
	this.custName = custName;
}



public String getPassword() {
	return password;
}



public void setPassword(String password) {
	this.password = password;
}



public long getId() {
	return id;
}



public ArrayList<Coupon> getCouponsCust() {
	return couponsCust;
}


public String toString() {
	
	return this.custName+this.id;
	


			
		
	}





}


