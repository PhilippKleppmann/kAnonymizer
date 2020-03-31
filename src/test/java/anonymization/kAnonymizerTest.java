package anonymization;

import dto.QuasiIdentifiers;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class kAnonymizerTest {

    private final String resourcesPrefix = "src/test/resources/";

    @Test
    public void testLoadData() throws IOException {
        final KAnonymizer kAnonymizer = new KAnonymizer(resourcesPrefix + "quasi-identifiers/firstTwoColumns.csv");

        final FrequencyList frequencyList = kAnonymizer.loadData(resourcesPrefix + "data/testData3Rows.csv");

        assertEquals(2, frequencyList.getData().size());
        assertEquals(2, frequencyList.getData().get(new QuasiIdentifiers(Arrays.asList("Bob", "20"))).size());
        assertEquals(1, frequencyList.getData().get(new QuasiIdentifiers(Arrays.asList("Alice", "25"))).size());
    }

}
