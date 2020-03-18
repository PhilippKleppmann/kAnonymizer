import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class kAnonymizerTest {

    @Test
    public void testCompactifyFrequencyList() {
        final KAnonymizer kAnonymizer = new KAnonymizer();

        final HashMultimap<List<String>, Integer> givenFrequencyList = HashMultimap.create();
        givenFrequencyList.put(Arrays.asList("a","b"), 2);
        givenFrequencyList.put(Arrays.asList("a","b"), 3);
        givenFrequencyList.put(Arrays.asList("a","c"), 1);

        final HashMultimap<List<String>, Integer> expectedFrequencyList = HashMultimap.create();
        expectedFrequencyList.put(Arrays.asList("a","b"), 5);
        expectedFrequencyList.put(Arrays.asList("a","c"), 1);

        final Multimap<List<String>, Integer> compactifiedFrequencyList = kAnonymizer.compactifyFrequencyList(givenFrequencyList);

        assertEquals(expectedFrequencyList, compactifiedFrequencyList);
    }

}