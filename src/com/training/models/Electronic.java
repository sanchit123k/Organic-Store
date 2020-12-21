package com.training.models;

import java.time.LocalDate;

public class Electronic extends Product {
	private String size;
	private int warranty;
	private int wattage;
	public Electronic() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Electronic(LocalDate dateOfSale, int itemCode, String itemName, double unitPrice, int quantity) {
		super(dateOfSale, itemCode, itemName, unitPrice, quantity);
		// TODO Auto-generated constructor stub
	}


	public Electronic(LocalDate dateOfSale, int itemCode, String itemName, double unitPrice, int quantity, String size, int warranty, int wattage) {
		super(dateOfSale, itemCode, itemName, unitPrice, quantity);
		this.size = size;
		this.warranty = warranty;
		this.wattage = wattage;
		// TODO Auto-generated constructor stub
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getWarranty() {
		return warranty;
	}
	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	public int getWattage() {
		return wattage;
	}
	public void setWattage(int wattage) {
		this.wattage = wattage;
	}
	@Override
	public String toString() {
		return "Electronic [size=" + size + ", warranty=" + warranty + ", wattage=" + wattage + ", toString()="
				+ super.toString() + "]";
	}
	@Override
	public double calculateTotalAmount() {
		// TODO Auto-generated method stub
		return getUnitPrice() * getQuantity();
	}
	
	
	
	
}
