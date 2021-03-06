package anonymization;

import com.google.common.annotations.VisibleForTesting;
import config.Hierarchy;
import dto.Dataset;
import java.util.List;

public class DataflyAlgorithm {
    private final int             k;
    private final List<Hierarchy> hierarchies;

    public DataflyAlgorithm(final int k, final List<Hierarchy> hierarchies) {
        this.k = k;
        this.hierarchies = hierarchies;
    }

    public FrequencyList kAnonymize(FrequencyList frequencyList) {

        while (continueGeneralizing(frequencyList)) {
            final int columnToGeneralize = frequencyList.findColumnToGeneralize();
            frequencyList = frequencyList.generalize(columnToGeneralize, hierarchies.get(columnToGeneralize));
        }

        frequencyList = frequencyList.suppressSmallEquivalenceClasses(k);

        return frequencyList;
    }

    // true if there are equivalence classes of size <k that account for >k elements
    @VisibleForTesting
    boolean continueGeneralizing(final FrequencyList frequencyList) {
        final Dataset data = frequencyList.getData();
        int allSmallEquivalenceClasses = data.keySet().stream()
                                             .map(quasiIdentifiers -> data.get(quasiIdentifiers).size())
                                             .filter(count -> count < k)
                                             .reduce(0, (countSum, count) -> countSum + count);

        return allSmallEquivalenceClasses > k;
    }
}
