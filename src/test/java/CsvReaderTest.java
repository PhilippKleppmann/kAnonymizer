import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CsvReaderTest {
    @Test
    public void testReadCsv() throws IOException, CsvValidationException {
        final CsvReader csvReader = new CsvReader();
        final Map<String, String> line = csvReader.readCsv("src/test/resources/testData.csv");
        assertEquals("Alice", line.get("name"));
        assertEquals("25", line.get("age"));
    }
}