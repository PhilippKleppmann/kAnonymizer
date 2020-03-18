import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FrequencyListTest {

    @Test
    public void testPut() {
        FrequencyList frequencyList = new FrequencyList();
        final List<String> ab = Arrays.asList("a", "b");
        final List<String> ac = Arrays.asList("a", "c");

        frequencyList.put(ab, 1);
        frequencyList.put(ab, 2);
        frequencyList.put(ac, 1);

        assertEquals(3, (int) frequencyList.data.get(ab));
        assertEquals(1, (int) frequencyList.data.get(ac));
    }
}