package poly.com.model;

import java.util.Date;

public class Item {
	private String name;
	private String image;
	private double price;
	private double discount;
	Date date = new Date();
	
	public Item() {
		
	}
	public Item(String name, String image, double price, double discount) {
		super();
		this.name = name;
		this.image = image;
		this.price = price;
		this.discount = discount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
