package javaBeans;
import java.util.*;
public class Company {

	private long ID;
	private String compName;
	private String password;
	private String email;
	ArrayList<Coupon> coupons  = new ArrayList();
/**
 * 
 * @param iD
 * @param compName
 * @param password
 * @param email
 * @param coupons
 */
public Company(long iD, String compName, String password, String email, ArrayList<Coupon> coupons) {
	super();
	ID = iD;
	this.compName = compName;
	this.password = password;
	this.email = email;
	this.coupons = coupons;
		}

public Company(long id2, String name, String password2, String email2) {
	// TODO Auto-generated constructor stub
}

public String getCompName() {
	return compName;
}

public void setcompName(String compName) {
	this.compName = compName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public long getID() {
	return ID;
}

public ArrayList<Coupon> getCoupons() {
	return coupons;
}
/**
 * returns: company name, ID , email
 */
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "company name: "+compName+ " ID: "+ID+" Email :"+ email;
	
}

			}