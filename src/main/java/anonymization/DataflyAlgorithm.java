package anonymization;

import anonymization.FrequencyList;
import com.google.common.annotations.VisibleForTesting;
import config.Hierarchy;
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
            frequencyList.generalize(columnToGeneralize, hierarchies.get(columnToGeneralize));
        }

        suppressSmallEquivalenceClasses(frequencyList);

        return frequencyList;
    }

    // true if there are equivalence classes of size <k that account for >k elements
    @VisibleForTesting
    boolean continueGeneralizing(final FrequencyList frequencyList) {
        int allSmallEquivalenceClasses = frequencyList.getData().entrySet().stream()
                                                      .map(entry -> entry.getCount())
                                                      .filter(count -> count < k)
                                                      .reduce(0, (countSum, count) -> countSum + count);

        return allSmallEquivalenceClasses > k;
    }

    @VisibleForTesting
    void suppressSmallEquivalenceClasses(final FrequencyList frequencyList) {
        frequencyList.getData().entrySet().stream()
                     .filter(entry -> entry.getCount() < k)
                     .forEach(entry -> frequencyList.suppressEquivalenceClass(entry.getElement()));
    }
}
