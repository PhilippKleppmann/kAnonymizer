import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.List;

public class FrequencyList {

    //generalization step:
    //* generate empty FrequencyList
    //* iterate over existing FrequencyList
    //  * generalize field if necessary
    //  * 'put' into new FrequencyList
    //The result will be a fresh generalized FrequencyList

    @VisibleForTesting
    Multiset<List<String>> data = HashMultiset.create();

    public void put(List<String> equivalenceClass) {
        data.add(equivalenceClass);
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
