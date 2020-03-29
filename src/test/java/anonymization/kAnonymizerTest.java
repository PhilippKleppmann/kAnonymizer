package anonymization;

import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class kAnonymizerTest {

    private final String resourcesPrefix = "src/test/resources/";

    @Test
    public void testLoadData() throws IOException {
        final KAnonymizer kAnonymizer = new KAnonymizer(resourcesPrefix + "firstTwoColumns.csv");

        final FrequencyList frequencyList = kAnonymizer.loadData(resourcesPrefix + "testData3Rows.csv",
                resourcesPrefix + "firstTwoColumns.csv");

        assertEquals(3, frequencyList.getData().size());
        assertEquals(2, frequencyList.getData().count(Arrays.asList("Bob", "20")));
        assertEquals(1, frequencyList.getData().count(Arrays.asList("Alice", "25")));
    }

}
