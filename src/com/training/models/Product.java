package com.training.models;

import java.time.LocalDate;  
import com.training.ifaces.Sale;

public abstract class Product implements Sale {
	
	private LocalDate dateOfSale;
	private int itemCode;
	private String itemName;
	private double unitPrice;
	private int quantity;
	
	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	



	public Product(LocalDate dateOfSale, int itemCode, String itemName, double unitPrice, int quantity) {
		super();
		this.dateOfSale = dateOfSale;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}











	public LocalDate getDateOfSale() {
		return dateOfSale;
	}





	public void setDateOfSale(LocalDate dateOfSale) {
		this.dateOfSale = dateOfSale;
	}





	public int getItemCode() {
		return itemCode;
	}





	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}





	public String getItemName() {
		return itemName;
	}





	public void setItemName(String itemName) {
		this.itemName = itemName;
	}





	public double getUnitPrice() {
		return unitPrice;
	}





	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}





	public int getQuantity() {
		return quantity;
	}





	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	
	



	@Override
	public String toString() {
		return "Product [itemCode=" + itemCode + ", itemName=" + itemName + ", unitPrice=" + unitPrice + ", quantity="
				+ quantity + "]";
	}





	@Override
	public double calculateTotalAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
