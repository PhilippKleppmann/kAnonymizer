import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.List;

public class KAnonymizer {

    public void anonymize(String filename) {
        Multimap<List<String>, Integer> data = loadData(filename);
    }

    private Multimap<List<String>, Integer> loadData(String filename) {
        return null;
    }

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
