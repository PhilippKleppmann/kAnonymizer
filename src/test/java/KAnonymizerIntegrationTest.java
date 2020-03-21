import java.io.IOException;
import org.junit.Test;

public class KAnonymizerIntegrationTest {

    private final String resourcesPrefix = "src/test/resources/";

    @Test
    public void testKAnonymizer() throws IOException {
        String genderHierarchy = resourcesPrefix + "ageHierarchy.csv";
        String nameHierarchy = resourcesPrefix + "nameHierarchy.csv";
        final KAnonymizer kAnonymizer = new KAnonymizer(nameHierarchy, genderHierarchy);
        kAnonymizer.anonymize(resourcesPrefix + "integrationTestTable.csv");
    }
}
