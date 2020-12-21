package com.training.services;

import java.time.LocalDate;
import java.time.Month;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.training.ifaces.DataAccess;
import com.training.models.Electronic;
import com.training.models.Garment;

public class GarmentService implements DataAccess<Garment>{
	
	private Connection connection;

	public GarmentService(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean add(Garment g) {
		// TODO Auto-generated method stub
		String sql = "Insert into garment values(?,?,?,?,?,?,?,?,?)";
		int rowAdded=0;
		try (PreparedStatement pstmt = connection.prepareStatement(sql);) {
			
			Date getDateOfSale = Date.valueOf(g.getDateOfSale());

			
			pstmt.setInt(1, 0);
			pstmt.setDate(2, getDateOfSale);
			pstmt.setInt(3, g.getItemCode());
			pstmt.setString(4, g.getItemName());
			pstmt.setDouble(5, g.getUnitPrice());
			pstmt.setInt(6, g.getQuantity());
			pstmt.setString(7, g.getSize());
			pstmt.setString(8, g.getMaterial());
			pstmt.setDouble(9, g.calculateTotalAmount());
			
			
			
			
			rowAdded = pstmt.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return rowAdded>0?true:false;
	}

	@Override
	public List<Garment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Garment> getReportByDate(LocalDate dateOfSaleKey) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM garment WHERE dateOfSale=?";
		ArrayList<Garment> garList = new ArrayList<>();
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
				String material = rst.getString("material");
				
				Garment garment = new Garment(dateOfSale,itemCode,itemName,unitPrice,quantity,size,material);
				garList.add(garment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return garList;
	}
	
	

	@Override
	public void printReportByDate(List<Garment> list, LocalDate dateOfSale) {
		// TODO Auto-generated method stub
		Iterator<Garment> itr = list.iterator();
		
		int count = 1;
		int totalQuantity = 0;
		double totalAmount = 0;
		System.out.format(" Garment Items Report for %d-%s-%d", dateOfSale.getDayOfMonth(), dateOfSale.getMonth(), dateOfSale.getYear());
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.format(" %s   | %6s   | %10s   | %6s   | %6s", "SI.No", "Item Name", "Unit Price", "Quantity", "Amount");
		System.out.println();
		System.out.println(" ==========================================================");
		while(itr.hasNext()) {
			Garment garment = itr.next();

			System.out.format(" %d       | %2s        | %.2f        | %d         | %.2f", count, garment.getItemName(), garment.getUnitPrice(), garment.getQuantity(), garment.calculateTotalAmount());
			System.out.println();
			totalQuantity = totalQuantity + garment.getQuantity();
			totalAmount = totalAmount + garment.calculateTotalAmount();
			count++;
		}
		
		System.out.format(" Total %40d           %.2f", totalQuantity, totalAmount);
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.println();
		
		
	}

	@Override
	public List<Garment> getTopThreeProductsSold(LocalDate dateOfSale) {
		// TODO Auto-generated method stub
		String sql = "SELECT itemName, sum(quantity) as q FROM garment WHERE MONTH(dateOfSale)=? GROUP BY itemName ORDER BY sum(quantity) desc";
		ArrayList<Garment> garList = new ArrayList<>();
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			
			pstmt.setObject(1, dateOfSale.getMonthValue());
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {
				
				String itemName = rst.getString("itemName");
				int quantity = rst.getInt("q");
				
				Garment electronic = new Garment(LocalDate.of(2014, Month.JANUARY, 1),0,itemName,0.00,quantity);
				garList.add(electronic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return garList;
	}

	@Override
	public void printTopThreeProductsSold(List<Garment> list, LocalDate dateOfSale) {
		// TODO Auto-generated method stub

		Iterator<Garment> itr = list.iterator();
		int count = 1;
		int totalQuantity = 0;
		System.out.format(" Top Selling Garment Items for the month of %s", dateOfSale.getMonth());
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.format(" %s   | %6s   | %6s", "SI.No", "Item Name", "Quantity");
		System.out.println();
		System.out.println(" ==========================================================");
		while(itr.hasNext()) {
			Garment garment = itr.next();
			System.out.format(" %d       | %2s        | %d", count, garment.getItemName(), garment.getQuantity());
			System.out.println();
			totalQuantity = totalQuantity + garment.getQuantity();
			count++;
		}

		System.out.format(" Total %20d           ", totalQuantity);
		System.out.println();
		System.out.println(" ==========================================================");
		System.out.println();

		
	}
	
	
	
	
	
	

	
}
