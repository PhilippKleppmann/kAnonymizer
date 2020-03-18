import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HierarchyTest {

    @Test
    public void testGeneralization() throws IOException {
        final Hierarchy genderHierarchy = new Hierarchy("src/test/resources/genderHierarchy.csv");
        assertEquals("*", genderHierarchy.generalize("Male"));
        assertEquals("*", genderHierarchy.generalize("Female"));
    }
}