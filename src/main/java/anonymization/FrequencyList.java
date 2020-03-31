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
    private final Set<Integer>      quasiIdentifierColumns;
    private final int               numberOfColumns;
    private final List<Set<String>> distinctValuesPerColumn = Lists.newArrayList();

    public FrequencyList(int numberOfColumns, Set<Integer> quasiIdentifierColumns) {
        this.quasiIdentifierColumns = quasiIdentifierColumns;
        this.numberOfColumns = numberOfColumns;
        for (int i = 0; i < numberOfColumns; ++i) {
            distinctValuesPerColumn.add(Sets.newHashSet());
        }
    }

    public Dataset getData() {
        return data;
    }

    public void put(QuasiIdentifiers quasiIdentifiers, NonIdentifiers nonIdentifiers) {
        data.add(quasiIdentifiers, nonIdentifiers);
        updateDistinctValuesPerColumn(quasiIdentifiers);
    }

    public void put(QuasiIdentifiers quasiIdentifiers, Multiset<NonIdentifiers> nonIdentifiers) {
        data.add(quasiIdentifiers, nonIdentifiers);
        updateDistinctValuesPerColumn(quasiIdentifiers);
    }

    private void updateDistinctValuesPerColumn(final QuasiIdentifiers quasiIdentifiers) {
        for (int i = 0; i < numberOfColumns; ++i) {
            final Set<String> distinctValues = distinctValuesPerColumn.get(i);
            distinctValues.add(quasiIdentifiers.get(i));
            distinctValuesPerColumn.set(i, distinctValues);
        }
    }

    //generalize the column that has most distinct values
    public int findColumnToGeneralize() {
        int maxDistinctColumnValues = 0;
        int columnToGeneralize = 0;
        for (int column : quasiIdentifierColumns) {
            if (distinctValuesPerColumn.get(column).size() > maxDistinctColumnValues) {
                maxDistinctColumnValues = distinctValuesPerColumn.get(column).size();
                columnToGeneralize = column;
            }
        }
        return columnToGeneralize;
    }

    public FrequencyList generalize(int columnToGeneralize, Hierarchy hierarchy) {
        FrequencyList result = new FrequencyList(numberOfColumns, quasiIdentifierColumns);

        for (QuasiIdentifiers quasiIdentifiers : data.keySet()) {
            final String generalizedValue = hierarchy.generalize(quasiIdentifiers.get(columnToGeneralize));
            final QuasiIdentifiers generalizedQuasiIdentifiers = new QuasiIdentifiers(quasiIdentifiers);
            generalizedQuasiIdentifiers.set(columnToGeneralize, generalizedValue);
            result.put(generalizedQuasiIdentifiers, data.get(quasiIdentifiers));
        }

        return result;
    }

    public FrequencyList suppressSmallEquivalenceClasses(int threshold) {
        FrequencyList result = new FrequencyList(numberOfColumns, quasiIdentifierColumns);

        data.keySet().stream()
            .filter(quasiIdentifiers -> data.get(quasiIdentifiers).size() >= threshold)
            .forEach(quasiIdentifiers -> result.put(quasiIdentifiers, data.get(quasiIdentifiers)));

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
