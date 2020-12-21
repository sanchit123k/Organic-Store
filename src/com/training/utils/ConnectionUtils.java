package com.training.utils;

import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.*;

// Login to mysql using $mysql -u root -p

public class ConnectionUtils {
	public static Connection getMySqlConnection() {
		
		Connection con = null;
		try {
		String fileName = "resources/DbConnection.properties";
		
		InputStream stream = ConnectionUtils.class.getClassLoader().getResourceAsStream(fileName);
		
		Properties props = new Properties();
		
			props.load(stream);
			String url = props.getProperty("database.url");
			String passWord = props.getProperty("database.passWord");
			String userName = props.getProperty("database.userName");
			
			con = DriverManager.getConnection(url,userName,passWord);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
//		System.out.println("Stream : " + stream);
		return con;
	}


	public static void main(String[] args) {
	
		System.out.println(ConnectionUtils.getMySqlConnection());
	}

}
