package javaBeans;

import java.sql.Date;

public class Coupon {
	long id;
	String title;
	Date startDate;
	Date endDate;
	int amount;
	CouponType type;
	String message;
	double price;
	String image;
	public Coupon(long id, String title, Date startDate, Date endDate, int amount,CouponType type, String message,
			double price, String image) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}
}
