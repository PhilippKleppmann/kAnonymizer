package anonymization;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataflyAlgorithmTest {

    private final List<String> alice      = Arrays.asList("Alice", "20");
    private final List<String> bob        = Arrays.asList("Bob", "25");
    private final List<String> clare      = Arrays.asList("Clare", "30");
    private final Set<Integer> allColumns = Sets.newHashSet(0, 1);

    @Test
    public void testContinueGeneralization() {
        final DataflyAlgorithm algorithm = new DataflyAlgorithm(2, null);
        FrequencyList frequencyList = new FrequencyList(2, allColumns);
        frequencyList.put(alice);
        frequencyList.put(bob);
        frequencyList.put(clare);

        assertTrue(algorithm.continueGeneralizing(frequencyList));
    }

    @Test
    public void testDiscontinueGeneralization() {
        final DataflyAlgorithm algorithm = new DataflyAlgorithm(2, null);
        FrequencyList frequencyList = new FrequencyList(2, allColumns);
        frequencyList.put(alice);
        frequencyList.put(bob);

        assertFalse(algorithm.continueGeneralizing(frequencyList));
    }
}
