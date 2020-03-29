package anonymization;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Multiset;
import config.Hierarchy;
import io.CsvHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KAnonymizer {

    private final List<Hierarchy> hierarchies = new ArrayList<>();
    private final Set<Integer>    quasiIdentifierColumns;

    public KAnonymizer(String quasiIdentifierColumnsFile, String... hierarchyFiles) throws IOException {
        quasiIdentifierColumns = CsvHandler.readCsv(quasiIdentifierColumnsFile).stream()
                                           .flatMap(List::stream)
                                           .map(Integer::parseInt)
                                           .collect(Collectors.toSet());

        for (String file : hierarchyFiles) {
            hierarchies.add(new Hierarchy(file));
        }
    }

    public void kAnonymize(String dataFile, int k) throws IOException {
        FrequencyList frequencyList = loadData(dataFile, null);
        frequencyList = new DataflyAlgorithm(k, hierarchies).kAnonymize(frequencyList);
        writeData(frequencyList, dataFile);
    }

    @VisibleForTesting
    FrequencyList loadData(String dataFile, String quasiIdentifierColumnsConfigFile) throws IOException {
        final List<List<String>> data = CsvHandler.readCsv(dataFile);
        final int numberOfColumns = data.get(0).size();

        final FrequencyList frequencyList = new FrequencyList(numberOfColumns, quasiIdentifierColumns);

        data.stream()
            .forEach(row -> frequencyList.put(row));

        return frequencyList;
    }

    private void writeData(FrequencyList frequencyList, final String dataFileName) throws IOException {
        final Multiset<List<String>> data = frequencyList.getData();
        CsvHandler.writeCsv(dataFileName + ".anon", data);
    }
}
