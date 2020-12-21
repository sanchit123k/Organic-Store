package com.training.services;

import java.time.LocalDate;
import java.time.Month;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.training.ifaces.DataAccess;
import com.training.models.Edible;
import com.training.models.Electronic;

public class ElectronicService implements DataAccess<Electronic>{
	
	private Connection connection;

	public ElectronicService(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean add(Electronic e) {
		// TODO Auto-generated method stub
		String sql = "Insert into electronic values(?,?,?,?,?,?,?,?,?,?)";
		int rowAdded=0;
		try (PreparedStatement pstmt = connection.prepareStatement(sql);) {
			
			Date getDateOfSale = Date.valueOf(e.getDateOfSale());
		
			pstmt.setInt(1, 0);
			pstmt.setDate(2, getDateOfSale);
			pstmt.setInt(3, e.getItemCode());
			pstmt.setString(4, e.getItemName());
			pstmt.setDouble(5, e.getUnitPrice());
			pstmt.setInt(6, e.getQuantity());
			pstmt.setString(7, e.getSize());
			pstmt.setInt(8, e.getWarranty());
			pstmt.setInt(9, e.getWattage());
			pstmt.setDouble(10, e.calculateTotalAmount());
						
			
			rowAdded = pstmt.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return rowAdded>0?true:false;
	}

	@Override
	public List<Electronic> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Electronic> getReportByDate(LocalDate dateOfSaleKey) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM electronic WHERE dateOfSale=?";
		ArrayList<Electronic> elecList = new ArrayList<>();
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setObject(1, dateOfSaleKey);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {
				
				LocalDate dateOfSale = rst.getDate("dateOfSale").toLocalDate();
				int itemCode = rst.getInt("itemCode");
				String itemName = rst.getString("itemName");
				double unitPrice = rst.getDouble("unitPrice");
				int quantity = rst.getInt("quantity");
				String size = rst.getString("size");
				int warranty = rst.getInt("warranty");
				int wattage = rst.getInt("wattage");
				
				Electronic electronic = new Electronic(dateOfSale,itemCode,itemName,unitPrice,quantity,size,warranty,wattage);
				elecList.add(electronic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elecList;
	}
	
	

	@Override
	public void printReportByDate(List<Electronic> list, LocalDate dateOfSale) {
		// TODO Auto-generated method stub
		
		Iterator<Electronic> itr = list.iterator();
		
		int count = 1;
		int totalQuantity = 0;
		double totalAmount = 0;
		System.out.format(" Electronic Items Report for %d-%s-%d", dateOfSale.getDayOfMonth(), dateOfSale.getMonth(), dateOfSale.getYear());
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.format(" %s   | %6s   | %10s   | %6s   | %6s", "SI.No", "Item Name", "Unit Price", "Quantity", "Amount");
		System.out.println();
		System.out.println(" ==========================================================");
		while(itr.hasNext()) {
			Electronic electronic = itr.next();

			System.out.format(" %d       | %2s        | %.2f        | %d         | %.2f", count, electronic.getItemName(), electronic.getUnitPrice(), electronic.getQuantity(), electronic.calculateTotalAmount());
			System.out.println();
			totalQuantity = totalQuantity + electronic.getQuantity();
			totalAmount = totalAmount + electronic.calculateTotalAmount();
			count++;
		}
		
		System.out.format(" Total %36d           %.2f", totalQuantity, totalAmount);
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.println();
		
		
	}

	@Override
	public List<Electronic> getTopThreeProductsSold(LocalDate dateOfSale) {
		// TODO Auto-generated method stub
		String sql = "SELECT itemName, sum(quantity) as q FROM electronic WHERE MONTH(dateOfSale)=? GROUP BY itemName ORDER BY sum(quantity) desc";
		ArrayList<Electronic> elecList = new ArrayList<>();
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setObject(1, dateOfSale.getMonthValue());
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {
				
				String itemName = rst.getString("itemName");
				int quantity = rst.getInt("q");
				
				Electronic electronic = new Electronic(LocalDate.of(2014, Month.JANUARY, 1),0,itemName,0.00,quantity);
				elecList.add(electronic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elecList;
	}

	@Override
	public void printTopThreeProductsSold(List<Electronic> list, LocalDate dateOfSale) {
		// TODO Auto-generated method stub
		
		Iterator<Electronic> itr = list.iterator();
		int count = 1;
		int totalQuantity = 0;
		System.out.format(" Top Selling Electronic Items for the month of %s", dateOfSale.getMonth());
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.format(" %s   | %6s   | %6s", "SI.No", "Item Name", "Quantity");
		System.out.println();
		System.out.println(" ==========================================================");
		while(itr.hasNext()) {
			Electronic electronic = itr.next();
			System.out.format(" %d       | %2s        | %d", count, electronic.getItemName(), electronic.getQuantity());
			System.out.println();
			totalQuantity = totalQuantity + electronic.getQuantity();
			count++;
		}

		System.out.format(" Total %20d           ", totalQuantity);
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.println();
		
	}
	
	
	
	
	
	
	
	
}
