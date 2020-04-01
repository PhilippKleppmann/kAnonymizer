package anonymization;

import com.google.common.annotations.VisibleForTesting;
import config.Hierarchy;
import dto.Dataset;
import dto.NonIdentifiers;
import dto.QuasiIdentifiers;
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
        FrequencyList frequencyList = loadData(dataFile);
        frequencyList = new DataflyAlgorithm(k, hierarchies).kAnonymize(frequencyList);
        writeData(frequencyList, dataFile);
    }

    @VisibleForTesting
    FrequencyList loadData(String dataFile) throws IOException {
        final List<List<String>> data = CsvHandler.readCsv(dataFile);

        final FrequencyList frequencyList = new FrequencyList(quasiIdentifierColumns.size());

        for (List<String> row : data) {
            QuasiIdentifiers quasiIdentifiers = new QuasiIdentifiers();
            NonIdentifiers nonIdentifiers = new NonIdentifiers();
            for (int i = 0; i < row.size(); ++i) {
                if (quasiIdentifierColumns.contains(i)) {
                    quasiIdentifiers.add(row.get(i));
                } else {
                    nonIdentifiers.add(row.get(i));
                }
            }
            frequencyList.add(quasiIdentifiers,nonIdentifiers);
        }

        return frequencyList;
    }

    private void writeData(FrequencyList frequencyList, final String dataFileName) throws IOException {
        final Dataset data = frequencyList.getData();
        CsvHandler.writeCsv(dataFileName + ".anon", data);
    }
}
