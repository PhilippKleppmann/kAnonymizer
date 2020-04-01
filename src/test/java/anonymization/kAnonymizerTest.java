package anonymization;

import dto.Dataset;
import dto.NonIdentifiers;
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

    @Test
    public void testLoadDataWithMixedQuasiAndNonIdentifiers() throws IOException {
        final KAnonymizer kAnonymizer = new KAnonymizer(resourcesPrefix + "quasi-identifiers/zeroAndTwo.csv");

        final FrequencyList frequencyList = kAnonymizer.loadData(resourcesPrefix + "data/testData4Columns.csv");

        final Dataset expectedDataset = new Dataset();
        final QuasiIdentifiers alice = new QuasiIdentifiers(Arrays.asList("Alice", "32"));
        final NonIdentifiers aliceNonIdentifiers = new NonIdentifiers(Arrays.asList("X", "XX"));
        final QuasiIdentifiers bob = new QuasiIdentifiers(Arrays.asList("Bob", "14"));
        final NonIdentifiers bobNonIdentifiers = new NonIdentifiers(Arrays.asList("Y", "YY"));
        expectedDataset.add(alice, aliceNonIdentifiers);
        expectedDataset.add(bob, bobNonIdentifiers);

        assertEquals(2, frequencyList.getData().size());
        assertEquals(expectedDataset, frequencyList.getData());
    }
}
