package com.epam.engx.cleancode.complextask.task1;

import java.util.List;

import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;

public class Table {

	private String tableName;
	private int rowCount;
	private int columnCount;
	private int maxColumnSize;
	private List<DataSet> dataSets;
	
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getColumnCount() {
		return columnCount;
	}
	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}
	public int getMaxColumnSize() {
		return maxColumnSize;
	}
	public void setMaxColumnSize(int maxColumnSize) {
		this.maxColumnSize = maxColumnSize;
	}
	
	
}
