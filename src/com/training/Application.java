package com.training;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.training.models.*;
import com.training.services.*;
import com.training.utils.ConnectionUtils;

// CREATE TABLE edible(orderId integer primary key AUTO_INCREMENT, dateOfSale date, itemCode integer, itemName varchar(20), unitPrice double, quantity integer, dateOfManufacture date, dateOfExpiry date, typeOfFood varchar(20), amount double);
// CREATE TABLE garment(orderId integer primary key AUTO_INCREMENT, dateOfSale date, itemCode integer, itemName varchar(20), unitPrice double, quantity integer, size varchar(10), material varchar(20), amount double);
// CREATE TABLE electronic(orderId integer primary key AUTO_INCREMENT, dateOfSale date, itemCode integer, itemName varchar(20), unitPrice double, quantity integer, size varchar(10), warranty integer, wattage integer, amount double);

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Logger log=Logger.getRootLogger();
		Connection connection = ConnectionUtils.getMySqlConnection();
		BasicConfigurator.configure();
		
		Edible edi1 = new Edible(LocalDate.of(2014, Month.JANUARY, 1), 101, "Eggs", 10.5, 4, LocalDate.of(2013, Month.DECEMBER, 29), LocalDate.of(2014, Month.JANUARY, 4), "Non-Veg");
		Edible edi2 = new Edible(LocalDate.of(2014, Month.FEBRUARY, 2), 102, "Milk", 50, 10, LocalDate.of(2014, Month.FEBRUARY, 1), LocalDate.of(2014, Month.FEBRUARY, 7), "Veg");
		Edible edi3 = new Edible(LocalDate.of(2014, Month.JANUARY, 3), 103, "Corn", 30.5, 20, LocalDate.of(2014, Month.JANUARY, 2), LocalDate.of(2014, Month.JANUARY, 8), "Veg");
		Edible edi4 = new Edible(LocalDate.of(2014, Month.MARCH, 4), 102, "Milk", 50, 10, LocalDate.of(2014, Month.MARCH, 3), LocalDate.of(2014, Month.MARCH, 10), "Veg");
		Edible edi5 = new Edible(LocalDate.of(2014, Month.JANUARY, 1), 101, "Eggs", 10.5, 12, LocalDate.of(2014, Month.DECEMBER, 31), LocalDate.of(2014, Month.JANUARY, 4), "Non-Veg");
		Edible edi6 = new Edible(LocalDate.of(2014, Month.MARCH, 4), 103, "Corn", 30.5, 10, LocalDate.of(2014, Month.MARCH, 2), LocalDate.of(2014, Month.MARCH, 7), "Veg");
		Edible edi7 = new Edible(LocalDate.of(2014, Month.MARCH, 7), 101, "Eggs", 10.5, 20, LocalDate.of(2014, Month.MARCH, 7), LocalDate.of(2014, Month.MARCH, 10), "Non-Veg");
		
		EdibleService edibleService = new EdibleService(connection);
		edibleService.add(edi1);
		edibleService.add(edi2);
		edibleService.add(edi3);
		edibleService.add(edi4);
		edibleService.add(edi5);
		edibleService.add(edi6);
		edibleService.add(edi7);
		
		LocalDate dateOfSale = LocalDate.of(2014, 1, 1);
		List<Edible> ediList1 = edibleService.getReportByDate(dateOfSale);
		log.info("Generating Reports By Data of Edible class");
		edibleService.printReportByDate(ediList1, dateOfSale);

		List<Edible> ediList2 = edibleService.getTopThreeProductsSold(dateOfSale);
		log.info("Generating Top Three Edibles Sold Report");
		edibleService.printTopThreeProductsSold(ediList2, dateOfSale);
		

		
		
		// =========================================================
		
		
		connection = ConnectionUtils.getMySqlConnection();
		
		Garment gar1 = new Garment(LocalDate.of(2014, Month.JANUARY, 1), 101, "Shirts", 1250, 10, "XL", "gents");
		Garment gar2 = new Garment(LocalDate.of(2014, Month.FEBRUARY, 2), 101, "Shirts", 2250, 40, "M", "ladies");
		Garment gar3 = new Garment(LocalDate.of(2014, Month.JANUARY, 3), 102, "Pants", 3750, 20, "XL", "gents");
		Garment gar4 = new Garment(LocalDate.of(2014, Month.MARCH, 4), 103, "Blazers", 9250, 20, "S", "unisex");
		Garment gar5 = new Garment(LocalDate.of(2014, Month.JANUARY, 1), 101, "Blazers", 1600, 10, "XLL", "ladies");
		Garment gar6 = new Garment(LocalDate.of(2014, Month.MARCH, 4), 103, "Shirts", 7250, 40, "M", "gents");
		Garment gar7 = new Garment(LocalDate.of(2014, Month.MARCH, 7), 102, "Pants", 2500, 10, "XL", "unisex");
				
		GarmentService garmentService = new GarmentService(connection);
		
		garmentService.add(gar1);
		garmentService.add(gar2);
		garmentService.add(gar3);
		garmentService.add(gar4);
		garmentService.add(gar5);
		garmentService.add(gar6);
		garmentService.add(gar7);
				
		List<Garment> garList1 = garmentService.getReportByDate(dateOfSale);
		log.info("Generating Report By Data of Garment class");
		garmentService.printReportByDate(garList1, dateOfSale);
		
		List<Garment> garList2 = garmentService.getTopThreeProductsSold(dateOfSale);
		log.info("Generating Top Three Garments Sold Report");
		garmentService.printTopThreeProductsSold(garList2, dateOfSale);
		
		
		// =========================================================

		connection = ConnectionUtils.getMySqlConnection();
	
		Electronic elec1 = new Electronic(LocalDate.of(2014, Month.JANUARY, 1), 101, "TV", 1250, 10, "42 inch", 10, 2500);
		Electronic elec2 = new Electronic(LocalDate.of(2014, Month.FEBRUARY, 2), 102, "AC", 2250, 40, "2 tonne", 5, 1500);
		Electronic elec3 = new Electronic(LocalDate.of(2014, Month.JANUARY, 3), 103, "Mobile", 3750, 20, "6 inch", 2, 75);
		Electronic elec4 = new Electronic(LocalDate.of(2014, Month.MARCH, 4), 102, "AC", 9250, 20, "1.5 tonne", 10, 1000);
		Electronic elec5 = new Electronic(LocalDate.of(2014, Month.JANUARY, 1), 103, "Mobile", 1600, 1, "5.5 inch", 1, 100);
		Electronic elec6 = new Electronic(LocalDate.of(2014, Month.MARCH, 4), 102, "AC", 7250, 40, "2.5 tonne", 8, 2000);
		Electronic elec7 = new Electronic(LocalDate.of(2014, Month.MARCH, 7), 101, "TV", 2500, 10, "35 inch", 10, 1000);
		
		ElectronicService electronicService = new ElectronicService(connection);
		
		electronicService.add(elec1);
		electronicService.add(elec2);
		electronicService.add(elec3);
		electronicService.add(elec4);
		electronicService.add(elec5);
		electronicService.add(elec6);
		electronicService.add(elec7);	
		
		List<Electronic> elecList1 = electronicService.getReportByDate(dateOfSale);
		log.info("Generating Report By Data of Electronic class");
		electronicService.printReportByDate(elecList1, dateOfSale);
		
		List<Electronic> elecList2 = electronicService.getTopThreeProductsSold(dateOfSale);
		log.info("Generating Top Three Electronics Sold Report");
		electronicService.printTopThreeProductsSold(elecList2, dateOfSale);

	}

}
