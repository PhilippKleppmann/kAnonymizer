import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.util.List;

public class KAnonymizer {

    public void anonymize(String filename) throws IOException {
        Multimap<List<String>, Integer> data = loadData(filename);
    }

    @VisibleForTesting
    Multimap<List<String>, Integer> loadData(String filename) throws IOException {
        final List<List<String>> table = CsvReader.readCsv(filename);
        return calculateFrequencies(table);
    }

    private Multimap<List<String>, Integer> calculateFrequencies(List<List<String>> table) {
        final HashMultimap<List<String>, Integer> result = HashMultimap.create();

        for (List<String> row : table) {
            result.put(row, 1);
        }

        return compactifyFrequencyList(result);
    }

    @VisibleForTesting
    Multimap<List<String>, Integer> compactifyFrequencyList(Multimap<List<String>, Integer> frequencyList) {
        final HashMultimap<List<String>, Integer> result = HashMultimap.create();

        for (List<String> equivalenceClass : frequencyList.keySet()) {
            int sizeOfEquivalenceClass = frequencyList.get(equivalenceClass)
                                                      .stream()
                                                      .reduce(0, (a, b) -> a + b);
            result.put(equivalenceClass, sizeOfEquivalenceClass);
        }

        return result;
    }
}
