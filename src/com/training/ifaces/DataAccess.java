package com.training.ifaces;

import java.time.LocalDate;  
import java.util.*;


public interface DataAccess<T> {
	public boolean add(T t);
	public List<T> findAll();
	public List<T> getReportByDate(LocalDate dateOfSale);
	public void printReportByDate(List<T> list, LocalDate dateOfSale);
	public List<T> getTopThreeProductsSold(LocalDate dateOfSale);
	public void printTopThreeProductsSold(List<T> list, LocalDate dateOfSale);
	
}
