package io;

import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvHandler {
    public static List<List<String>> readCsv(String fileName) throws IOException {
        final List<List<String>> fields = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)
                                                .stream()
                                                .filter(row -> row.length() > 0)
                                                .map(line -> Arrays.asList(line.split(",")))
                                                .map(CsvHandler::trimAll)
                                                .collect(Collectors.toList());
        if (validateNumberOfColumns(fields)) {
            return fields;
        } else {
            throw new RuntimeException("Validation of CSV failed: Not all rows have the same length");
        }
    }

    public static void writeCsv(String fileName, Iterable<List<String>> fields) throws IOException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");

        for (List<String> fieldsRow : fields) {
            final String row = String.join(",", fieldsRow);
            writer.println(row);
        }

        writer.close();
    }

    private static List<String> trimAll(List<String> row) {
        return row.stream()
                .map(field -> field.trim())
                .collect(Collectors.toList());
    }

    @VisibleForTesting
    static boolean validateNumberOfColumns(List<List<String>> fields) {
        if (fields.isEmpty()) {
            return true;
        }

        int numberOfColumns = fields.get(0).size();
        for (List<String> row : fields) {
            if (row.size() != numberOfColumns) {
                return false;
            }
        }

        return true;
    }
}
