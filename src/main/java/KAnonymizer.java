import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.util.List;

public class KAnonymizer {

    public void anonymize(String filename) throws IOException {
        FrequencyList frequencyList = loadData(filename);
    }

    @VisibleForTesting
    FrequencyList loadData(String filename) throws IOException {
        final List<List<String>> table = CsvReader.readCsv(filename);
        final FrequencyList frequencyList = new FrequencyList();

        for (List<String> row : table) {
            frequencyList.put(row, 1);
        }
        return frequencyList;
    }
}
