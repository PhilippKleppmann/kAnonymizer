import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Multiset;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KAnonymizer {

    private final List<Hierarchy> hierarchies  = new ArrayList<>();

    public KAnonymizer(String... hierarchyFileNames) throws IOException {
        for (String fileName : hierarchyFileNames) {
            hierarchies.add(new Hierarchy(fileName));
        }
    }

    public void anonymize(String dataFileName) throws IOException {
        FrequencyList frequencyList = loadData(dataFileName);

        int columnToGeneralize = frequencyList.findColumnToGeneralize();
        frequencyList.generalize(columnToGeneralize, hierarchies.get(columnToGeneralize));
        columnToGeneralize = frequencyList.findColumnToGeneralize();
        frequencyList.generalize(columnToGeneralize, hierarchies.get(columnToGeneralize));

        writeData(frequencyList, dataFileName);
    }

    @VisibleForTesting
    FrequencyList loadData(String filename) throws IOException {
        final List<List<String>> table = CsvHandler.readCsv(filename);

        final FrequencyList frequencyList = new FrequencyList(table.get(0).size());

        for (List<String> row : table) {
            frequencyList.put(row);
        }
        return frequencyList;
    }

    private void writeData(FrequencyList frequencyList, final String dataFileName) throws IOException {
        final Multiset<List<String>> data = frequencyList.getData();
        CsvHandler.writeCsv(dataFileName + ".anon", data);
    }
}
