package com.epam.engx.cleancode.complextask.task1;
import static com.epam.engx.cleancode.complextask.task1.PrintTable.TableBorders.*;


import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.View;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DatabaseManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintTable implements Command {

    private static final int ALLOWED_PARAMETERS = 1;
    private static final String SEPARATOR = " ";
    private static final String COMMAND_TEXT = "print ";
    private static final String EMPTY_TABLE_MESSAGE = "Table '%s' is empty or does not exist";

    private View view;
    private DatabaseManager dbManager;
    private String tableName;
    private int columnWidth;
    private int columnCount;

    public PrintTable(View view, DatabaseManager manager) {
        this.view = view;
        this.dbManager = manager;
    }

    public boolean canProcess(String command) {
        return command.startsWith(COMMAND_TEXT);
    }

    public void process(String input) {
        validateCommand(input);

        tableName = getTableNameFromCommand(input);
        List<DataSet> data = dbManager.getTableData(tableName);
        view.write(tableToString(data));
    }

    private void validateCommand(String commandString) {
        String[] commandElements = commandString.split(SEPARATOR);
        int commandParametersNumber = commandElements.length - 1;

        if (commandParametersNumber != ALLOWED_PARAMETERS) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + commandParametersNumber);
        }
    }

    private String getTableNameFromCommand(String commandString) {
        return commandString.split(SEPARATOR)[1];
    }

    private String tableToString(List<DataSet> data) {
        if (data.isEmpty()) {
            return getEmptyTable(tableName);
        }

        return getFullTable(data);
    }

    private String getEmptyTable(String tableName) {
        String textEmptyTable = String.format(EMPTY_TABLE_MESSAGE, tableName);
        columnWidth = calculateColumnWidthWithPadding(textEmptyTable);
        columnCount = 1;
        List<String> value = Collections.singletonList(textEmptyTable);

        String result = printTableBorder(TOP_BORDER);
        result += printRow(value);
        result += printTableBorder(BOTTOM_BORDER);

        return result;
    }

    private String getFullTable(List<DataSet> data) {
        columnWidth = calculateColumnWidthWithPadding(data);
        columnCount = getColumnCount(data);

        String result = printTableBorder(TOP_BORDER);
        result += printTableHeader(data);
        result += printTableBorder(INNER_BODER);
        result += printTableBody(data);
        result += printTableBorder(BOTTOM_BORDER);

        return result;
    }

    private int calculateColumnWidthWithPadding(List<DataSet> dataSets) {
        int maxColumnWidth = getMaxColumnWidth(dataSets);
        int paddingSize = (maxColumnWidth % 2 == 0) ? 2 : 3;

        return maxColumnWidth + paddingSize;
    }

    private int calculateColumnWidthWithPadding(String cellValue) {
        int paddingSize = 2;
        return cellValue.length() + paddingSize;
    }

    private int getMaxColumnWidth(List<DataSet> dataSets) {
        if (dataSets.isEmpty()) {
            return 0;
        }

        int maxColumnNameLength = getMaxValueLengthInRow(dataSets.get(0).getColumnNames());
        int maxValueLength = getMaxValueLength(dataSets);

        return Math.max(maxColumnNameLength, maxValueLength) ;
    }

    private int getMaxValueLength(List<DataSet> dataSets) {
        int maxLength = 0;

        for (DataSet dataSet : dataSets) {
            List<String> values = convertObjectListToStringList(dataSet.getValues());
            int maxLengthInRow = getMaxValueLengthInRow(values);
            maxLength = Math.max(maxLength, maxLengthInRow);
        }

        return maxLength;
    }

    private int getMaxValueLengthInRow(List<String> values) {
        int maxLength = 0;

        for (String value : values) {
            maxLength = Math.max(value.length(), maxLength);
        }

        return maxLength;
    }

    private int getColumnCount(List<DataSet> dataSets) {
        if (dataSets.isEmpty()) {
            return 0;
        }

        return dataSets.get(0).getColumnNames().size();
    }

    private String printTableBorder(TableBorders borderStyle) {
        StringBuilder result = new StringBuilder(borderStyle.leftJoint);
        for (int j = 1; j < columnCount; j++) {
            result.append(repeatSymbol(borderStyle.lineElement, columnWidth));
            result.append(borderStyle.innerJoint);
        }

        result.append(repeatSymbol(borderStyle.lineElement, columnWidth));
        result.append(borderStyle.rightJoint);
        result.append("\n");

        return result.toString();
    }

    private String printTableHeader(List<DataSet> dataSets) {
        return printRow(dataSets.get(0).getColumnNames());
    }

    private String printTableBody(List<DataSet> dataSets) {
        String result = "";
        int rowsCount = dataSets.size();

        for (int rowNum = 0; rowNum < rowsCount; rowNum++) {
            List<String> values = convertObjectListToStringList(dataSets.get(rowNum).getValues());
            result += printRow(values);
            result += printInnerTableBorderIfNeeded(rowsCount, rowNum);
        }

        return result;
    }

    private String printInnerTableBorderIfNeeded(int rowsCount, int rowNum) {
        String result = "";
        boolean isNotLastRow = rowNum < rowsCount - 1;
        if (isNotLastRow) {
            result += printTableBorder(INNER_BODER);
        }
        return result;
    }

    private String printRow(List<String> values) {
        StringBuilder result = new StringBuilder();
        for (int columnNum = 0; columnNum < columnCount; columnNum++) {
            result.append("║");
            String value = values.get(columnNum);
            result.append(printCellValue(value));
        }
        result.append("║\n");
        return result.toString();
    }

    private String printCellValue(String cellValue) {
        int valueLength = String.valueOf(cellValue).length();
        int leftPadding = (columnWidth - valueLength) / 2;
        int rightPadding = (valueLength % 2 == 0) ? leftPadding : leftPadding + 1;

        String result = repeatSymbol(" ", leftPadding);
        result += cellValue;
        result += repeatSymbol(" ", rightPadding);

        return result;
    }

    private String repeatSymbol(String symbol, int times) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(symbol);
        }

        return result.toString();
    }

    private List<String> convertObjectListToStringList(List<Object> values) {
        List<String> result = new ArrayList<>(values.size());
        for (Object value : values) {
            result.add(String.valueOf(value));
        }
        return result;
    }


    enum TableBorders {
        TOP_BORDER("╔", "╦", "╗", "═"),
        INNER_BODER("╠", "╬", "╣", "═"),
        BOTTOM_BORDER("╚", "╩", "╝", "═");

        public final String leftJoint;
        public final String innerJoint;
        public final String rightJoint;
        public final String lineElement;

        TableBorders(String leftJoint, String innerJoint, String rightJoint, String lineElement) {
            this.leftJoint = leftJoint;
            this.innerJoint = innerJoint;
            this.rightJoint = rightJoint;
            this.lineElement = lineElement;
        }
    }

}
