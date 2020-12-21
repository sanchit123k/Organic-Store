package com.training.models;

import java.time.LocalDate;



public class Edible extends Product {
	private LocalDate dateOfManufacture;
	private LocalDate dateOfExpiry;
	private String typeOfFood;
	
	

	public Edible() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Edible(LocalDate dateOfSale, int itemCode, String itemName, double unitPrice, int quantity) {
		super(dateOfSale, itemCode, itemName, unitPrice, quantity);
		// TODO Auto-generated constructor stub
	}
	
	public Edible(LocalDate dateOfSale, int itemCode, String itemName, double unitPrice, int quantity, LocalDate dateOfManufacture, LocalDate dateOfExpiry, String typeOfFood) {
		super(dateOfSale, itemCode, itemName, unitPrice, quantity);
		this.dateOfManufacture = dateOfManufacture;
		this.dateOfExpiry = dateOfExpiry;
		this.typeOfFood = typeOfFood;
	}
	
	
	public LocalDate getDateOfManufacture() {
		return dateOfManufacture;
	}
	public void setDateOfManufacture(LocalDate dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}
	public LocalDate getDateOfExpiry() {
		return dateOfExpiry;
	}
	public void setDateOfExpiry(LocalDate dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	public String getTypeOfFood() {
		return typeOfFood;
	}
	public void setTypeOfFood(String typeOfFood) {
		this.typeOfFood = typeOfFood;
	}
	@Override
	public String toString() {
		return "Edible [dateOfManufacture=" + dateOfManufacture + ", dateOfExpiry=" + dateOfExpiry + ", typeOfFood="
				+ typeOfFood + ", getItemCode()=" + getItemCode() + ", getItemName()=" + getItemName()
				+ ", getUnitPrice()=" + getUnitPrice() + ", getQuantity()=" + getQuantity() + "]";
	}
	@Override
	public double calculateTotalAmount() {
		// TODO Auto-generated method stub
		return getUnitPrice() * getQuantity();
	}
	
	
	
}
