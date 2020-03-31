package dto;

import java.util.ArrayList;
import java.util.Collection;

public class QuasiIdentifiers extends ArrayList<String> {
    public QuasiIdentifiers(Collection<String> fields) {
        addAll(fields);
    }
}
