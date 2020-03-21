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

    private       Multiset<List<String>> data                    = HashMultiset.create();
    private final List<Set<String>>      distinctValuesPerColumn = Lists.newArrayList();
    private final int                    numberOfColumns;

    public FrequencyList(int numberOfColumns) {
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
        for (int i = 0; i < distinctValuesPerColumn.size(); ++i) {
            if (distinctValuesPerColumn.get(i).size() > maxDistinctColumnValues) {
                maxDistinctColumnValues = distinctValuesPerColumn.get(i).size();
                columnToGeneralize = i;
            }
        }
        return columnToGeneralize;
    }

    public FrequencyList generalize(int columnToGeneralize, Hierarchy hierarchy) {
        FrequencyList result = new FrequencyList(numberOfColumns);

        for (List<String> row : data) {
            final String generalizedValue = hierarchy.generalize(row.get(columnToGeneralize));
            final List<String> generalizedRow = new ArrayList<>(row);
            generalizedRow.set(columnToGeneralize, generalizedValue);
            result.put(generalizedRow);
        }
        return result;
    }

    public void suppressEquivalenceClass(List<String> equivalenceClass) {
        data.remove(equivalenceClass, data.count(equivalenceClass));

        for (int i = 0; i < distinctValuesPerColumn.size(); ++i) {
            distinctValuesPerColumn.remove(equivalenceClass.get(i));
        }
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
