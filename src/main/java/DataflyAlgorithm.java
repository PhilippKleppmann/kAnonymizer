import java.util.List;

public class DataflyAlgorithm {
    public static FrequencyList kAnonymize(FrequencyList frequencyList, int k, final List<Hierarchy> hierarchies) {

        int columnToGeneralize = frequencyList.findColumnToGeneralize();
        frequencyList.generalize(columnToGeneralize, hierarchies.get(columnToGeneralize));
        columnToGeneralize = frequencyList.findColumnToGeneralize();
        frequencyList.generalize(columnToGeneralize, hierarchies.get(columnToGeneralize));

        return frequencyList;
    }
}
