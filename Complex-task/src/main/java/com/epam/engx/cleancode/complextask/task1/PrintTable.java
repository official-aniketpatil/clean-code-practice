package com.epam.engx.cleancode.complextask.task1;



import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.View;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DatabaseManager;

import java.util.List;


public class PrintTable implements Command {

	private static final String COMMAND_SEPARATOR = " ";
	private static final int ALLOWED_PARAMETERS_NUMBER = 1;
	private static final String COMMAND_TEXT = "print ";
    
	private View view;
    private DatabaseManager databaseManager;
    private String tableName;

    public PrintTable(View view, DatabaseManager manager) {
        this.view = view;
        this.databaseManager = manager;
    }
    public boolean canProcess(String command) {
        return command.startsWith("print ");
    }
    
    public void process(String command) {
        validateCommand(command);
    	tableName = getTableNameFromCommand(command);
        List<DataSet> dataSets = databaseManager.getTableData(tableName);
        view.write(getTableString(dataSets));
    }
    private void validateCommand(String commandString) {
    	String[] command = commandString.split(COMMAND_SEPARATOR);
        int commandParameters = command.length - 1;

        if (commandParameters != ALLOWED_PARAMETERS_NUMBER) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + (command.length - 1));
        }

    }
    private String getTableNameFromCommand(String commandString) {
        return commandString.split(COMMAND_SEPARATOR)[1];
    }
    private String getFullTable(List<DataSet> data) {
    	return getHeaderOfTheTable(data) + getStringTableData(data);
    }
    private String getTableString(List<DataSet> data) {
        int maxColumnSize;
        maxColumnSize = getMaxColumnWidth(data);
        if (maxColumnSize == 0) {
            return getEmptyTable(tableName);
        } else {
            return getFullTable(data);
        }
    }
    private String getHorizontalBorder(int length) {
    	String result = "";
    	for (int i = 0; i < length; i++) {
            result += "═";
        }
    	return result;
    }
    private String getEmptyTable(String tableName) {
        String textEmptyTable = "║ Table '" + tableName + "' is empty or does not exist ║";
        String result = "╔";
        result += getHorizontalBorder(textEmptyTable.length());
        result += "╗\n";
        result += textEmptyTable + "\n";
        result += "╚";
        result += getHorizontalBorder(textEmptyTable.length());
        result += "╝\n";
        return result;
    }
    private int getMaxColumnSizeByColumnName(List<DataSet> dataSets) {
    	int maxLength=0;
    	List<String> columnNames = dataSets.get(0).getColumnNames();
        for (String columnName : columnNames) {
            if (columnName.length() > maxLength) {
                maxLength = columnName.length();
            }
        }
        return maxLength;
    }
    private int getMaxColumnSizeByValue(List<DataSet> dataSets) {
    	int maxLength = 0;
    	for (DataSet dataSet : dataSets) {
            List<Object> values = dataSet.getValues();
            for (Object value : values) {
                if (value instanceof String) {
                    if (String.valueOf(value).length() > maxLength) {
                        maxLength = String.valueOf(value).length();
                    }
                }
            }
        }
    	return maxLength;
    }
    private int getMaxColumnWidth(List<DataSet> dataSets) {
        int maxLength = 0;
        
        if (dataSets.size() > 0) {
            int maxLengthByColumnName = getMaxColumnSizeByColumnName(dataSets);
            int maxLengthByValues = getMaxColumnSizeByValue(dataSets);
            if(maxLengthByColumnName > maxLengthByValues) {
            	maxLength = maxLengthByColumnName;
            }else {
            	maxLength = maxLengthByValues;
            }
        }
        return maxLength;
    }
    private String getBlankSpaces(int length) {
    	String res = "";
    	for(int i = 0; i < length; i++) {
    		res += " ";
    	}
    	return res;
    }
    
    private String getStringTableData(List<DataSet> dataSets) {
        int rowsCount;
        rowsCount = dataSets.size();
        int maxColumnSize = getMaxColumnWidth(dataSets);
        String result = "";
        if (maxColumnSize % 2 == 0) {
            maxColumnSize += 2;
        } else {
            maxColumnSize += 3;
        }
        int columnCount = getColumnCount(dataSets);
        for (int row = 0; row < rowsCount; row++) {
            List<Object> values = dataSets.get(row).getValues();
            result += "║";
            for (int column = 0; column < columnCount; column++) {
                int valuesLength = String.valueOf(values.get(column)).length();
                result += getBlankSpaces((maxColumnSize - valuesLength) / 2);
                result += String.valueOf(values.get(column));
                result += getBlankSpaces((maxColumnSize - valuesLength) / 2);
                result += "║";
            }
            result += "\n";
            if (row < rowsCount - 1) {
                result += "╠";
                for (int j = 1; j < columnCount; j++) {
                    result += getHorizontalBorder(maxColumnSize);
                    result += "╬";
                }
                result += getHorizontalBorder(maxColumnSize);
                result += "╣\n";
            }
        }
        result += "╚";
        for (int j = 1; j < columnCount; j++) {
            for (int i = 0; i < maxColumnSize; i++) {
                result += "═";
            }
            result += "╩";
        }
        for (int i = 0; i < maxColumnSize; i++) {
            result += "═";
        }
        result += "╝\n";
        return result;
    }

    private int getColumnCount(List<DataSet> dataSets) {
        int result = 0;
        if (dataSets.size() > 0) {
            return dataSets.get(0).getColumnNames().size();
        }
        return result;
    }

    private String getHeaderOfTheTable(List<DataSet> dataSets) {
        int maxColumnSize = getMaxColumnWidth(dataSets);
        String result = "";
        int columnCount = getColumnCount(dataSets);
        if (maxColumnSize % 2 == 0) {
            maxColumnSize += 2;
        } else {
            maxColumnSize += 3;
        }
        result += "╔";
        for (int j = 1; j < columnCount; j++) {
            result += getHorizontalBorder(maxColumnSize);
            result += "╦";
        }
        result += getHorizontalBorder(maxColumnSize);
        result += "╗\n";
        List<String> columnNames = dataSets.get(0).getColumnNames();
        for (int column = 0; column < columnCount; column++) {
            result += "║";
            int columnNamesLength = columnNames.get(column).length();
            if (columnNamesLength % 2 == 0) {
                result += getBlankSpaces((maxColumnSize - columnNamesLength) / 2);
                result += columnNames.get(column);
                result += getBlankSpaces((maxColumnSize - columnNamesLength) / 2);
            } else {
            	result += getBlankSpaces((maxColumnSize - columnNamesLength) / 2);
                result += columnNames.get(column);
                result += getBlankSpaces((maxColumnSize - columnNamesLength) / 2);
            }
        }
        result += "║\n";

        //last string of the header
        if (dataSets.size() > 0) {
            result += "╠";
            for (int j = 1; j < columnCount; j++) {
                result += getHorizontalBorder(maxColumnSize);
                result += "╬";
            }
            result += getHorizontalBorder(maxColumnSize);
            result += "╣\n";
        } else {
            result += "╚";
            for (int j = 1; j < columnCount; j++) {
            	result += getHorizontalBorder(maxColumnSize);
                result += "╩";
            }
            result += getHorizontalBorder(maxColumnSize);
            result += "╝\n";
        }
        return result;
    }
}