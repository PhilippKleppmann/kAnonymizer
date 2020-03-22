import anonymization.KAnonymizer;
import java.io.IOException;
import org.junit.Test;

public class KAnonymizationIntegrationTest {

    private final String resourcesPrefix = "src/test/resources/";

    @Test
    public void testKAnonymizer() throws IOException {
        String ageHierarchy = resourcesPrefix + "ageHierarchy.csv";
        String nameHierarchy = resourcesPrefix + "nameHierarchy.csv";
        final KAnonymizer kAnonymizer = new KAnonymizer(nameHierarchy, ageHierarchy);

        kAnonymizer.kAnonymize(resourcesPrefix + "integrationTestTable.csv", 2);
    }

    @Test
    public void anonymizeAdults() throws IOException {
        String ageHierarchy = resourcesPrefix + "ageHierarchy.csv";
        String educationHierarchy = resourcesPrefix + "educationHierarchy.csv";
        String genderHierarchy = resourcesPrefix + "genderHierarchy.csv";
        String maritalHierarchy = resourcesPrefix + "maritalHierarchy.csv";
        String countryHierarchy = resourcesPrefix + "countryHierarchy.csv";
        final KAnonymizer kAnonymizer = new KAnonymizer(ageHierarchy,
                educationHierarchy, maritalHierarchy, genderHierarchy, countryHierarchy);

        kAnonymizer.kAnonymize(resourcesPrefix + "adults.csv", 2);
    }
}
