package anonymization;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import config.Hierarchy;
import dto.Dataset;
import dto.NonIdentifiers;
import dto.QuasiIdentifiers;
import java.util.List;
import java.util.Set;

public class FrequencyList {

    private final Dataset           data                    = new Dataset();
    private final List<Set<String>> distinctValuesPerColumn = Lists.newArrayList();
    private final int               numberOfQuasiIdentifiers;

    public FrequencyList(int numberOfQuasiIdentifiers) {
        this.numberOfQuasiIdentifiers = numberOfQuasiIdentifiers;
        for (int i = 0; i < numberOfQuasiIdentifiers; ++i) {
            distinctValuesPerColumn.add(Sets.newHashSet());
        }
    }

    public Dataset getData() {
        return data;
    }

    public void add(QuasiIdentifiers quasiIdentifiers, NonIdentifiers nonIdentifiers) {
        data.add(quasiIdentifiers, nonIdentifiers);
        updateDistinctValuesPerColumn(quasiIdentifiers);
    }

    public void add(QuasiIdentifiers quasiIdentifiers, Multiset<NonIdentifiers> nonIdentifiers) {
        data.add(quasiIdentifiers, nonIdentifiers);
        updateDistinctValuesPerColumn(quasiIdentifiers);
    }

    //keep track of distinct values per column in order to find column to generalize
    private void updateDistinctValuesPerColumn(final QuasiIdentifiers quasiIdentifiers) {
        for (int i = 0; i < numberOfQuasiIdentifiers; ++i) {
            final Set<String> distinctValues = distinctValuesPerColumn.get(i);
            distinctValues.add(quasiIdentifiers.get(i));
            distinctValuesPerColumn.set(i, distinctValues);
        }
    }

    //generalize the column that has most distinct values
    public int findColumnToGeneralize() {
        int maxDistinctColumnValues = 0;
        int columnToGeneralize = 0;
        for (int i = 0; i < numberOfQuasiIdentifiers; ++i) {
            if (distinctValuesPerColumn.get(i).size() > maxDistinctColumnValues) {
                maxDistinctColumnValues = distinctValuesPerColumn.get(i).size();
                columnToGeneralize = i;
            }
        }
        return columnToGeneralize;
    }

    public FrequencyList generalize(int columnToGeneralize, Hierarchy hierarchy) {
        FrequencyList result = new FrequencyList(numberOfQuasiIdentifiers);

        for (QuasiIdentifiers quasiIdentifiers : data.keySet()) {
            final String generalizedValue = hierarchy.generalize(quasiIdentifiers.get(columnToGeneralize));
            final QuasiIdentifiers generalizedQuasiIdentifiers = new QuasiIdentifiers(quasiIdentifiers);
            generalizedQuasiIdentifiers.set(columnToGeneralize, generalizedValue);
            result.add(generalizedQuasiIdentifiers, data.get(quasiIdentifiers));
        }

        return result;
    }

    public FrequencyList suppressSmallEquivalenceClasses(int threshold) {
        FrequencyList result = new FrequencyList(numberOfQuasiIdentifiers);

        data.keySet().stream()
            .filter(quasiIdentifiers -> data.get(quasiIdentifiers).size() >= threshold)
            .forEach(quasiIdentifiers -> result.add(quasiIdentifiers, data.get(quasiIdentifiers)));

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FrequencyList frequencyList = (FrequencyList) obj;
        return data.equals(frequencyList.data);
    }
}
