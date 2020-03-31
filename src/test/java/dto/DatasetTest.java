package dto;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatasetTest {
    @Test
    public void testPutForIdenticalData() {
        final Dataset data = new Dataset();
        final List<String> empty = Arrays.asList();
        final QuasiIdentifiers quasiIdentifiers = new QuasiIdentifiers(empty);
        final NonIdentifiers nonIdentifiers = new NonIdentifiers(empty);
        data.add(quasiIdentifiers, nonIdentifiers);
        data.add(quasiIdentifiers, nonIdentifiers);
        assertEquals(1, data.size());
        assertEquals(2, data.get(quasiIdentifiers).size());
    }
}