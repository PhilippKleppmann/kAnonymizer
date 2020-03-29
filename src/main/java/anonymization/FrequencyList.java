package anonymization;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import config.Hierarchy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FrequencyList {

    private final Multiset<List<String>> data                    = HashMultiset.create();
    private final Set<Integer>           quasiIdentifierColumns;
    private final int                    numberOfColumns;
    private final List<Set<String>>      distinctValuesPerColumn = Lists.newArrayList();

    public FrequencyList(int numberOfColumns, Set<Integer> quasiIdentifierColumns) {
        this.quasiIdentifierColumns = quasiIdentifierColumns;
        this.numberOfColumns = numberOfColumns;
        for (int i = 0; i < numberOfColumns; ++i) {
            distinctValuesPerColumn.add(Sets.newHashSet());
        }
    }

    public Multiset<List<String>> getData() {
        return data;
    }

    public void put(List<String> row) {
        data.add(row);

        for (int i = 0; i < numberOfColumns; ++i) {
            final Set<String> distinctValues = distinctValuesPerColumn.get(i);
            distinctValues.add(row.get(i));
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

        for (List<String> row : data) {
            final String generalizedValue = hierarchy.generalize(row.get(columnToGeneralize));
            final List<String> generalizedRow = new ArrayList<>(row);
            generalizedRow.set(columnToGeneralize, generalizedValue);
            result.put(generalizedRow);
        }
        return result;
    }

    public FrequencyList suppressSmallEquivalenceClasses(int threshold) {
        FrequencyList result = new FrequencyList(numberOfColumns, quasiIdentifierColumns);

        data.stream()
            .filter(row -> data.count(row) >= threshold)
            .forEach(row -> result.put(row));

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
