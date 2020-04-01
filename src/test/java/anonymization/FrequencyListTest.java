package anonymization;

import com.google.common.collect.Sets;
import config.Hierarchy;
import dto.NonIdentifiers;
import dto.QuasiIdentifiers;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class FrequencyListTest {

    private final QuasiIdentifiers alice      = new QuasiIdentifiers(Arrays.asList("Alice", "20"));
    private final QuasiIdentifiers bob21      = new QuasiIdentifiers(Arrays.asList("Bob", "21"));
    private final QuasiIdentifiers bob22      = new QuasiIdentifiers(Arrays.asList("Bob", "22"));
    private final NonIdentifiers   empty      = new NonIdentifiers(Arrays.asList());
    private final Set<Integer>     allColumns = Sets.newHashSet(0, 1);

    @Test
    public void testFindColumnToGeneralize() {
        final FrequencyList frequencyList = new FrequencyList(allColumns);
        frequencyList.put(alice, empty);
        frequencyList.put(bob21, empty);
        frequencyList.put(bob22, empty);

        int columnToGeneralize = frequencyList.findColumnToGeneralize();

        assertEquals(1, columnToGeneralize);
    }

    @Test
    public void testGeneralize() throws IOException {
        final Hierarchy nameHierarchy = new Hierarchy("src/test/resources/hierarchies/nameHierarchy.csv");

        FrequencyList frequencyList = new FrequencyList(allColumns);
        frequencyList.put(alice, empty);
        frequencyList.put(bob21, empty);
        frequencyList.put(bob22, empty);

        final FrequencyList expectedFrequencyList = new FrequencyList(allColumns);
        expectedFrequencyList.put(new QuasiIdentifiers(Arrays.asList("A", "20")), empty);
        expectedFrequencyList.put(new QuasiIdentifiers(Arrays.asList("B", "21")), empty);
        expectedFrequencyList.put(new QuasiIdentifiers(Arrays.asList("B", "22")), empty);

        frequencyList = frequencyList.generalize(0, nameHierarchy);

        assertEquals(expectedFrequencyList, frequencyList);
    }

    @Test
    public void testSuppressSmallEquivalenceClasses() {
        FrequencyList frequencyList = new FrequencyList(allColumns);
        frequencyList.put(alice, empty);
        frequencyList.put(bob21, empty);
        frequencyList.put(bob21, empty);

        FrequencyList expectedFrequencyList = new FrequencyList(allColumns);
        expectedFrequencyList.put(bob21, empty);
        expectedFrequencyList.put(bob21, empty);

        frequencyList = frequencyList.suppressSmallEquivalenceClasses(2);

        assertEquals(expectedFrequencyList, frequencyList);
    }
}