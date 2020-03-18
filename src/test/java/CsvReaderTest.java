import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CsvReaderTest {

    @Test
    public void testReadCsv() throws IOException {
        final List<List<String>> csv = CsvReader.readCsv("src/test/resources/testData1Row.csv");
        assertEquals(1, csv.size());
        assertEquals(Arrays.asList("Alice", "25"), csv.get(0));
    }

}