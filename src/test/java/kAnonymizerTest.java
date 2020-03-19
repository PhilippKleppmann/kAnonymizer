import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class kAnonymizerTest {

    @Test
    public void testLoadData() throws IOException {
        final KAnonymizer kAnonymizer = new KAnonymizer();

        final FrequencyList frequencyList = kAnonymizer.loadData("src/test/resources/testData3Rows.csv");

        final FrequencyList expectedFrequencyList = new FrequencyList();
        expectedFrequencyList.put(Arrays.asList("Bob", "20"), 2);
        expectedFrequencyList.put(Arrays.asList("Alice", "25"), 1);

        assertEquals(expectedFrequencyList, frequencyList);
    }

}
