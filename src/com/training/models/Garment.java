package com.training.models;

import java.time.LocalDate; 


public class Garment extends Product {
	private String size;
	private String material;
	public Garment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Garment(LocalDate dateOfSale, int itemCode, String itemName, double unitPrice, int quantity) {
		super(dateOfSale, itemCode, itemName, unitPrice, quantity);
		// TODO Auto-generated constructor stub
	}



	public Garment(LocalDate dateOfSale, int itemCode, String itemName, double unitPrice, int quantity, String size, String material) {
		super(dateOfSale, itemCode, itemName, unitPrice, quantity);
		this.size = size;
		this.material = material;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Garment [size=" + size + ", material=" + material + "]";
	}

	@Override
	public double calculateTotalAmount() {
		// TODO Auto-generated method stub
		return getUnitPrice() * getQuantity();
	}
	
	
	
}
