package io;

import dto.Dataset;
import dto.NonIdentifiers;
import dto.QuasiIdentifiers;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CsvHandlerTest {

    private final String       resourcesPrefix = "src/test/resources/";
    private final List<String> alice           = Arrays.asList("Alice", "25");

    @Test
    public void testReadWriteCsv() throws IOException {
        List<List<String>> data = Arrays.asList(alice);

        CsvHandler.writeCsv(resourcesPrefix + "data/testWriteCsv.csv", data);
        final List<List<String>> csv = CsvHandler.readCsv(resourcesPrefix + "data/testWriteCsv.csv");

        assertEquals(1, csv.size());
    }

    @Test
    public void testReadWriteDataset() throws IOException {
        final Dataset dataset = new Dataset();
        final QuasiIdentifiers quasiIdentifiers = new QuasiIdentifiers(Arrays.asList("a", "b"));
        final NonIdentifiers nonIdentifiers = new NonIdentifiers(Arrays.asList("c", "d"));
        dataset.add(quasiIdentifiers, nonIdentifiers);

        CsvHandler.writeCsv(resourcesPrefix + "data/testWriteDataset.csv", dataset);
        final List<List<String>> csv = CsvHandler.readCsv(resourcesPrefix + "data/testWriteDataset.csv");

        assertEquals(1, csv.size());
        assertEquals(Arrays.asList("a","b","c","d"), csv.get(0));
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