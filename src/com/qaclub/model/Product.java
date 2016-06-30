package com.qaclub.model;

public class Product {
	private String price;
	private String title;
	private String link;

	public Product(String link, String price, String title) {
		this.setLink(link);
		this.setPrice(price);
		this.setTitle(title);
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
