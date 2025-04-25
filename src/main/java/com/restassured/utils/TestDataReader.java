package com.restassured.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class TestDataReader {
    public static List<Map<String, String>> readCSV(String filePath) {
        List<Map<String, String>> testData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();
            if (rows.size() > 1) { // Check if we have header and data
                String[] headers = rows.get(0);
                for (int i = 1; i < rows.size(); i++) {
                    String[] row = rows.get(i);
                    Map<String, String> rowData = java.util.stream.IntStream
                            .range(0, headers.length)
                            .boxed()
                            .collect(Collectors.toMap(
                                    j -> headers[j],
                                    j -> row[j]
                            ));
                    testData.add(rowData);
                }
            }
        } catch (IOException | CsvException e) {
            log.error("Error reading CSV file: " + e.getMessage());
            throw new RuntimeException("Failed to read test data from CSV: " + e.getMessage());
        }
        return testData;
    }
} 