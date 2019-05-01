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

	/**
	 * 
	 * @param id
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param type
	 * @param message
	 * @param price
	 * @param image
	 */
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

	public Coupon(long coupondId, String title2, Date startDate2, Date endDate2, int i, int j, CouponType type2) {
		// TODO Auto-generated constructor stub
	}
}
