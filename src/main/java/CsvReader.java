import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvReader {
    public Map<String, String> readCsv(String fileName) throws IOException, CsvValidationException {
        return new CSVReaderHeaderAware(new FileReader(fileName)).readMap();
    }

    public static List<String[]> readFile(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)
                    .stream()
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
    }
}
