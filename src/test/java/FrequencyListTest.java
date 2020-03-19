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
}