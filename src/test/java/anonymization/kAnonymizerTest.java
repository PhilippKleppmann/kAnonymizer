package anonymization;

import anonymization.FrequencyList;
import anonymization.KAnonymizer;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class kAnonymizerTest {

    @Test
    public void testLoadData() throws IOException {
        final KAnonymizer kAnonymizer = new KAnonymizer();

        final FrequencyList frequencyList = kAnonymizer.loadData("src/test/resources/testData3Rows.csv");

        assertEquals(3, frequencyList.getData().size());
        assertEquals(2, frequencyList.getData().count(Arrays.asList("Bob", "20")));
        assertEquals(1, frequencyList.getData().count(Arrays.asList("Alice", "25")));
    }

}
