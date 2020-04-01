package dto;

import java.util.ArrayList;
import java.util.Collection;

public class NonIdentifiers extends ArrayList<String> {
    public NonIdentifiers(Collection<String> fields) {
        addAll(fields);
    }

    public NonIdentifiers() {
        super();
    }
}
