package com.utils.commons.lib;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import init.env.global.vars.EnvVars;

import javax.swing.*;

public class FileReader {
    public static LinkedList<String> getRecordsInCSVFile(String csvFilePathPath, int columnInput) throws Exception {
        LinkedList<String> list = new LinkedList<String>();
        Scanner scanner = new Scanner(new File(csvFilePathPath));
        while (scanner.hasNext()) {
            List<String> line = csvParserLine(scanner.nextLine());
            list.add(line.get(columnInput));
        }
        scanner.close();
        return list;
    }

    public static List<String> csvParserLine(String cvsLine) {
        return csvParserLine(cvsLine, EnvVars.DEFAULT_SEPARATOR, EnvVars.DEFAULT_QUOTE);
    }
    public static List<String> csvParserLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = EnvVars.DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = EnvVars.DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());
        return result;
    }

    public static LinkedList<String> loadTotalRecordsFromCSVFile(String pathInput){
        LinkedList<String> itemsInCsvFile = null;//new LinkedList<>();
        try {
            //TODO Consider change to ArrayList for better cache management
            itemsInCsvFile = getRecordsInCSVFile(pathInput, 0);
            if (itemsInCsvFile.size() > 0) {
                //setRecordsInCSVFile(itemsInCsvFile);
                System.out.println("Loaded " + itemsInCsvFile.size() + " records from CSV...Done");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load path " + pathInput, "CSV cannot be loaded", JOptionPane.ERROR_MESSAGE);
        }
        return itemsInCsvFile;
    }
}
