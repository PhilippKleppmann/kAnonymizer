import anonymization.KAnonymizer;
import io.CsvHandler;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class KAnonymizationIntegrationTest {

    private final String resourcesPrefix = "src/test/resources/";

    @Test
    public void testKAnonymizer() throws IOException {
        String ageHierarchy = resourcesPrefix + "hierarchies/ageHierarchy.csv";
        String nameHierarchy = resourcesPrefix + "hierarchies/nameHierarchy.csv";
        String quasiIdentifiers = resourcesPrefix + "quasi-identifiers/firstTwoColumns.csv";
        final KAnonymizer kAnonymizer = new KAnonymizer(quasiIdentifiers, nameHierarchy, ageHierarchy);

        kAnonymizer.kAnonymize(resourcesPrefix + "data/integrationTestTable.csv", 2);

        final List<List<String>> anonymizedTable = CsvHandler.readCsv(resourcesPrefix + "data/integrationTestTable.csv.anon");
        assertEquals(2, anonymizedTable.size());
        assertEquals(Arrays.asList("A", "[21-25]"), anonymizedTable.get(0));
        assertEquals(Arrays.asList("A", "[21-25]"), anonymizedTable.get(1));
    }

    @Test
    public void anonymizeAdults() throws IOException {
        String ageHierarchy = resourcesPrefix + "hierarchies/ageHierarchy.csv";
        String educationHierarchy = resourcesPrefix + "hierarchies/educationHierarchy.csv";
        String genderHierarchy = resourcesPrefix + "hierarchies/genderHierarchy.csv";
        String maritalHierarchy = resourcesPrefix + "hierarchies/maritalHierarchy.csv";
        String countryHierarchy = resourcesPrefix + "hierarchies/countryHierarchy.csv";
        String quasiIdentifiers = resourcesPrefix + "quasi-identifiers/firstFiveColumns.csv";
        final KAnonymizer kAnonymizer = new KAnonymizer(quasiIdentifiers, ageHierarchy,
                educationHierarchy, maritalHierarchy, genderHierarchy, countryHierarchy);

        kAnonymizer.kAnonymize(resourcesPrefix + "data/adults.csv", 2);
    }
}
