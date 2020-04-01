package dto;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.Arrays;
import java.util.HashMap;

public class Dataset extends HashMap<QuasiIdentifiers, Multiset<NonIdentifiers>> {
    public void add(QuasiIdentifiers quasiIdentifiers, NonIdentifiers nonIdentifiers) {
        if (containsKey(quasiIdentifiers)) {
            get(quasiIdentifiers).add(nonIdentifiers);
        } else {
            put(quasiIdentifiers, HashMultiset.create(Arrays.asList(nonIdentifiers)));
        }
    }

    public void add(QuasiIdentifiers quasiIdentifiers, Multiset<NonIdentifiers> nonIdentifiersSet) {
        if (containsKey(quasiIdentifiers)) {
            nonIdentifiersSet.stream()
                             .forEach(nonIdentifiers -> get(quasiIdentifiers).add(nonIdentifiers));
        } else {
            put(quasiIdentifiers, nonIdentifiersSet);
        }
    }

}
