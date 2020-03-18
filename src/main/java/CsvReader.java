import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {
    public static List<List<String>> readCsv(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)
                    .stream()
                    .map(line -> Arrays.asList(line.split(",")))
                    .collect(Collectors.toList());
    }
}
