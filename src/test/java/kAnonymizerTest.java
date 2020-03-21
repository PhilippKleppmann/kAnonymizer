import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class kAnonymizerTest {

    @Test
    public void testLoadData() throws IOException {
        final KAnonymizer kAnonymizer = new KAnonymizer();

        final FrequencyList frequencyList = kAnonymizer.loadData("src/test/resources/testData3Rows.csv");

        assertEquals(3, frequencyList.data.size());
        assertEquals(2, frequencyList.data.count(Arrays.asList("Bob", "20")));
        assertEquals(1, frequencyList.data.count(Arrays.asList("Alice", "25")));
    }

}