package anonymization;

import com.google.common.collect.Sets;
import config.Hierarchy;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class FrequencyListTest {

    private final List<String> alice      = Arrays.asList("Alice", "20");
    private final List<String> bob21      = Arrays.asList("Bob", "21");
    private final List<String> bob22      = Arrays.asList("Bob", "22");
    private final Set<Integer> allColumns = Sets.newHashSet(0, 1);

    @Test
    public void testFindColumnToGeneralize() {
        final FrequencyList frequencyList = new FrequencyList(2, allColumns);
        frequencyList.put(alice);
        frequencyList.put(bob21);
        frequencyList.put(bob22);

        int columnToGeneralize = frequencyList.findColumnToGeneralize();

        assertEquals(1, columnToGeneralize);
    }

    @Test
    public void testGeneralize() throws IOException {
        final Hierarchy nameHierarchy = new Hierarchy("src/test/resources/hierarchies/nameHierarchy.csv");

        FrequencyList frequencyList = new FrequencyList(2, allColumns);
        frequencyList.put(alice);
        frequencyList.put(bob21);
        frequencyList.put(bob22);

        final FrequencyList expectedFrequencyList = new FrequencyList(2, allColumns);
        expectedFrequencyList.put(Arrays.asList("A", "20"));
        expectedFrequencyList.put(Arrays.asList("B", "21"));
        expectedFrequencyList.put(Arrays.asList("B", "22"));

        frequencyList = frequencyList.generalize(0, nameHierarchy);

        assertEquals(expectedFrequencyList, frequencyList);
    }

    @Test
    public void testSuppressSmallEquivalenceClasses() {
        FrequencyList frequencyList = new FrequencyList(2, allColumns);
        frequencyList.put(alice);
        frequencyList.put(bob21);
        frequencyList.put(bob21);

        FrequencyList expectedFrequencyList = new FrequencyList(2, allColumns);
        expectedFrequencyList.put(bob21);
        expectedFrequencyList.put(bob21);

        frequencyList = frequencyList.suppressSmallEquivalenceClasses(2);

        assertEquals(expectedFrequencyList, frequencyList);
    }
}