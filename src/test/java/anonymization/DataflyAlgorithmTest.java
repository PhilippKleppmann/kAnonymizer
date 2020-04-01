package anonymization;

import com.google.common.collect.Sets;
import dto.NonIdentifiers;
import dto.QuasiIdentifiers;
import java.util.Arrays;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataflyAlgorithmTest {

    private final QuasiIdentifiers alice      = new QuasiIdentifiers(Arrays.asList("Alice", "20"));
    private final QuasiIdentifiers bob        = new QuasiIdentifiers(Arrays.asList("Bob", "25"));
    private final QuasiIdentifiers clare      = new QuasiIdentifiers(Arrays.asList("Clare", "30"));
    private final NonIdentifiers   empty      = new NonIdentifiers(Arrays.asList());
    private final Set<Integer>     allColumns = Sets.newHashSet(0, 1);

    @Test
    public void testContinueGeneralization() {
        final DataflyAlgorithm algorithm = new DataflyAlgorithm(2, null);
        FrequencyList frequencyList = new FrequencyList(allColumns.size());
        frequencyList.add(alice, empty);
        frequencyList.add(bob, empty);
        frequencyList.add(clare, empty);

        assertTrue(algorithm.continueGeneralizing(frequencyList));
    }

    @Test
    public void testDiscontinueGeneralization() {
        final DataflyAlgorithm algorithm = new DataflyAlgorithm(2, null);
        FrequencyList frequencyList = new FrequencyList(allColumns.size());
        frequencyList.add(alice, empty);
        frequencyList.add(bob, empty);

        assertFalse(algorithm.continueGeneralizing(frequencyList));
    }
}
