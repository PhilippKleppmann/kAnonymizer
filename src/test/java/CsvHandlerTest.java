import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CsvHandlerTest {

    @Test
    public void testReadCsv() throws IOException {
        final List<List<String>> csv = CsvHandler.readCsv("src/test/resources/testData1Row.csv");
        assertEquals(1, csv.size());
        assertEquals(Arrays.asList("Alice", "25"), csv.get(0));
    }

    @Test
    public void testWriteCsv() throws IOException {
        List<String> row = Arrays.asList("Alice");
        List<List<String>> data = Arrays.asList(row);
        CsvHandler.writeCsv("src/test/resources/testWriteCsv.csv", data);
    }
}