package com.training.services;

import java.sql.*;

import java.time.LocalDate;
import java.time.Month;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.training.ifaces.DataAccess;
import com.training.models.Edible;
import com.training.models.Product;

public class EdibleService implements DataAccess<Edible>{
	private Connection connection;

	public EdibleService(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean add(Edible e) {
		// TODO Auto-generated method stub
		String sql = "Insert into edible values(?,?,?,?,?,?,?,?,?,?)";
		int rowAdded=0;
		try (PreparedStatement pstmt = connection.prepareStatement(sql);) {
			
			Date getDateOfSale = Date.valueOf(e.getDateOfSale());
			Date getDateOfManufacture = Date.valueOf(e.getDateOfManufacture());
			Date getDateOfExpiry = Date.valueOf(e.getDateOfExpiry());
			
			pstmt.setInt(1, 0);
			pstmt.setDate(2, getDateOfSale);
			pstmt.setInt(3, e.getItemCode());
			pstmt.setString(4, e.getItemName());
			pstmt.setDouble(5, e.getUnitPrice());
			pstmt.setInt(6, e.getQuantity());
			pstmt.setDate(7, getDateOfManufacture);
			pstmt.setDate(8, getDateOfExpiry);
			pstmt.setString(9, e.getTypeOfFood());
			pstmt.setDouble(10, e.calculateTotalAmount());
			
			
			
			
			rowAdded = pstmt.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return rowAdded>0?true:false;
	}

	@Override
	public List<Edible> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Edible> getReportByDate(LocalDate dateOfSaleKey) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM edible WHERE dateOfSale=?";
		ArrayList<Edible> ediList = new ArrayList<>();
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setObject(1, dateOfSaleKey);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {
				
				LocalDate dateOfSale = rst.getDate("dateOfSale").toLocalDate();
				int itemCode = rst.getInt("itemCode");
				String itemName = rst.getString("itemName");
				double unitPrice = rst.getDouble("unitPrice");
				int quantity = rst.getInt("quantity");
				LocalDate dateOfManufacture = rst.getDate("dateOfManufacture").toLocalDate();
				LocalDate dateOfExpiry = rst.getDate("dateOfExpiry").toLocalDate();
				String typeOfFood = rst.getString("typeOfFood");
				
				Edible edible = new Edible(dateOfSale,itemCode,itemName,unitPrice,quantity,dateOfManufacture,dateOfExpiry,typeOfFood);
				ediList.add(edible);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ediList;
	}
	
	

	@Override
	public void printReportByDate(List<Edible> list, LocalDate dateOfSale) {
		// TODO Auto-generated method stub
		Iterator<Edible> itr = list.iterator();
		
		int count = 1;
		int totalQuantity = 0;
		double totalAmount = 0;
		System.out.format(" Edible Items Report for %d-%s-%d", dateOfSale.getDayOfMonth(), dateOfSale.getMonth(), dateOfSale.getYear());
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.format(" %s   | %6s   | %10s   | %6s   | %6s", "SI.No", "Item Name", "Unit Price", "Quantity", "Amount");
		System.out.println();
		System.out.println(" ==========================================================");
		while(itr.hasNext()) {
			Edible edible = itr.next();

			System.out.format(" %d       | %2s        | %.2f        | %d         | %.2f", count, edible.getItemName(), edible.getUnitPrice(), edible.getQuantity(), edible.calculateTotalAmount());
			System.out.println();
			totalQuantity = totalQuantity + edible.getQuantity();
			totalAmount = totalAmount + edible.calculateTotalAmount();
			count++;
		}
		
		System.out.format(" Total %35d           %.2f", totalQuantity, totalAmount);
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.println();
	}

	@Override
	public List<Edible> getTopThreeProductsSold(LocalDate dateOfSale) {
		// TODO Auto-generated method stub
		String sql = "SELECT itemName, sum(quantity) as q FROM edible WHERE MONTH(dateOfSale)=? GROUP BY itemName ORDER BY sum(quantity) desc";
		ArrayList<Edible> ediList = new ArrayList<>();
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setObject(1, dateOfSale.getMonthValue());
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {
				
				String itemName = rst.getString("itemName");
				int quantity = rst.getInt("q");
				
				Edible edible = new Edible(LocalDate.of(2014, Month.JANUARY, 1),0,itemName,0.00,quantity);
				ediList.add(edible);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ediList;
		
	}

	@Override
	public void printTopThreeProductsSold(List<Edible> list, LocalDate dateOfSale) {
		// TODO Auto-generated method stub
		Iterator<Edible> itr = list.iterator();
		int count = 1;
		int totalQuantity = 0;
		System.out.format(" Top Selling Edible Items for the month of %s", dateOfSale.getMonth());
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.format(" %s   | %6s   | %6s", "SI.No", "Item Name", "Quantity");
		System.out.println();
		System.out.println(" ==========================================================");
		while(itr.hasNext()) {
			Edible edible = itr.next();
			System.out.format(" %d       | %2s        | %d", count, edible.getItemName(), edible.getQuantity());
			System.out.println();
			totalQuantity = totalQuantity + edible.getQuantity();
			count++;
		}

		System.out.format(" Total %20d           ", totalQuantity);
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.println();

	}
	
	
	
	
	
	
	
	
}
