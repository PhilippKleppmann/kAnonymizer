package anonymization;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Multiset;
import config.Hierarchy;
import io.CsvHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KAnonymizer {

    private final List<Hierarchy> hierarchies = new ArrayList<>();

    public KAnonymizer(String... hierarchyFileNames) throws IOException {
        for (String fileName : hierarchyFileNames) {
            hierarchies.add(new Hierarchy(fileName));
        }
    }

    public void kAnonymize(String dataFileName, int k) throws IOException {
        FrequencyList frequencyList = loadData(dataFileName);
        frequencyList = new DataflyAlgorithm(k, hierarchies).kAnonymize(frequencyList);
        writeData(frequencyList, dataFileName);
    }

    @VisibleForTesting
    FrequencyList loadData(String filename) throws IOException {
        final List<List<String>> table = CsvHandler.readCsv(filename);

        final int numberOfColumns = table.get(0).size();
        final FrequencyList frequencyList = new FrequencyList(numberOfColumns);

        table.stream()
             .forEach(row -> frequencyList.put(row));

        return frequencyList;
    }

    private void writeData(FrequencyList frequencyList, final String dataFileName) throws IOException {
        final Multiset<List<String>> data = frequencyList.getData();
        CsvHandler.writeCsv(dataFileName + ".anon", data);
    }
}
