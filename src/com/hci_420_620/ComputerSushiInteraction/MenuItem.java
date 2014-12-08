package com.hci_420_620.ComputerSushiInteraction;

public class MenuItem {	
	String name;
	String description;
	double price;
	int imageId;
	
	public MenuItem(String name, String description, double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public int getImageId() {
		return this.imageId;
	}

	public void setImageId(int image) {
		this.imageId = image;
	}

	public double getPrice() {
		return price;
	}
	
	public String getPriceString() {
		return String.format("$%.2f",price);
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
