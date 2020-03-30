package dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class DatasetTest {
    @Test
    public void testPutForIdenticalData() {
        final Dataset data = new Dataset();
        final QuasiIdentifiers quasiIdentifiers = new QuasiIdentifiers();
        final NonIdentifiers nonIdentifiers = new NonIdentifiers();
        data.put(quasiIdentifiers, nonIdentifiers);
        data.put(quasiIdentifiers, nonIdentifiers);
        assertEquals(1, data.size());
        assertEquals(2, data.get(quasiIdentifiers).size());
    }
}