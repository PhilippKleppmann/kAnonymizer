import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CsvReader {
    public Map<String, String> readCsv(String fileName) throws IOException, CsvValidationException {
        return new CSVReaderHeaderAware(new FileReader(fileName)).readMap();
    }
}
