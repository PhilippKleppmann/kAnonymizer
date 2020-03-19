import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KAnonymizer {

    private final List<Hierarchy> hierarchies;

    public KAnonymizer(String... hierarchyFileNames) throws IOException {
        hierarchies = loadHierarchies(hierarchyFileNames);
    }

    public void anonymize(String filename) throws IOException {
        FrequencyList frequencyList = loadData(filename);
    }

    @VisibleForTesting
    FrequencyList loadData(String filename) throws IOException {
        final List<List<String>> table = CsvReader.readCsv(filename);

        final FrequencyList frequencyList = new FrequencyList(table.get(0).size());

        for (List<String> row : table) {
            frequencyList.put(row);
        }
        return frequencyList;
    }

    List<Hierarchy> loadHierarchies(String... fileNames) throws IOException {
        final List<Hierarchy> hierarchies = Arrays.asList();

        for (String fileName : fileNames) {
            hierarchies.add(new Hierarchy(fileName));
        }

        return hierarchies;
    }
}
