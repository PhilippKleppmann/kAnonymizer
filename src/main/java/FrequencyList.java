import com.google.common.annotations.VisibleForTesting;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyList {

    //generalization step:
    //* generate empty FrequencyList
    //* iterate over existing FrequencyList
    //  * generalize field if necessary
    //  * 'put' into new FrequencyList
    //The result will be a fresh generalized FrequencyList

    @VisibleForTesting
    Map<List<String>, Integer> data = new HashMap<>();

    public void put(List<String> key, Integer value) {
        if (data.containsKey(key)) {
            data.put(key, data.get(key) + value);
        } else {
            data.put(key, value);
        }
    }

}
