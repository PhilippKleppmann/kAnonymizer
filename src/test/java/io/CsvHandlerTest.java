package io;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CsvHandlerTest {

    private static List<String> alice = Arrays.asList("Alice", "25");

    @Test
    public void testReadCsv() throws IOException {
        final List<List<String>> csv = CsvHandler.readCsv("src/test/resources/testData1Row.csv");
        assertEquals(1, csv.size());
        assertEquals(alice, csv.get(0));
    }

    @Test
    public void testWriteCsv() throws IOException {
        List<List<String>> data = Arrays.asList(alice);
        CsvHandler.writeCsv("src/test/resources/testWriteCsv.csv", data);
    }

    @Test
    public void testValidateNumberOfColumns() {
        List<List<String>> fields = Arrays.asList(alice, alice);
        final boolean validateNumberOfColumns = CsvHandler.validateNumberOfColumns(fields);
        assertTrue(validateNumberOfColumns);
    }

    @Test
    public void testFailingValidateNumberOfColumns() {
        List<List<String>> fields = Arrays.asList(alice, Arrays.asList("Alice"));
        final boolean validateNumberOfColumns = CsvHandler.validateNumberOfColumns(fields);
        assertFalse(validateNumberOfColumns);
    }
}