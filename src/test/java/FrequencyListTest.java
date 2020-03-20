import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class FrequencyListTest {

    @Test
    public void testFindColumnToGeneralize() {
        final FrequencyList frequencyList = new FrequencyList(2);
        frequencyList.put(Arrays.asList("Alice", "20"));
        frequencyList.put(Arrays.asList("Bob", "21"));
        frequencyList.put(Arrays.asList("Bob", "22"));

        int columnToGeneralize = frequencyList.findColumnToGeneralize();

        assertEquals(1, columnToGeneralize);
    }

    @Test
    public void testGeneralize() throws IOException {
        final Hierarchy nameHierarchy = new Hierarchy("src/test/resources/nameHierarchy.csv");

        final FrequencyList frequencyList = new FrequencyList(2);
        frequencyList.put(Arrays.asList("Alice", "20"));
        frequencyList.put(Arrays.asList("Bob", "21"));
        frequencyList.put(Arrays.asList("Bob", "22"));

        final FrequencyList expectedFrequencyList = new FrequencyList(2);
        expectedFrequencyList.put(Arrays.asList("A", "20"));
        expectedFrequencyList.put(Arrays.asList("B", "21"));
        expectedFrequencyList.put(Arrays.asList("B", "22"));

        final FrequencyList generalizedFrequencyList = frequencyList.generalize(0, nameHierarchy);

        assertEquals(expectedFrequencyList, generalizedFrequencyList);
    }
}