package com.epam.engx.cleancode.naming.task6;

public class Formatter {

    private static final String PLUS = "+";
    private static final String PIPE = "|";
    private static final String MINUS = "-";
    private static final String UNDERSCORE = " _ ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) {
        System.out.println(formatKeyValue("enable", "true"));
        System.out.println(formatKeyValue("name", "Bob"));
    }

    private static String formatKeyValue(String key, String value) {
        String content = key + UNDERSCORE + value;
        String minuses = buildPattern(MINUS, content.length());
        
        return PLUS +  minuses + PLUS + NEW_LINE
                + PIPE + content + PIPE + NEW_LINE +
                PLUS + minuses + PLUS + NEW_LINE;
    }

    private static String buildPattern(String symbol, int times) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < times; i++)
            result.append(symbol);
        return result.toString();
    }
}
