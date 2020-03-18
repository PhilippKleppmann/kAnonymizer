import java.io.IOException;
import java.util.List;

public class Hierarchy {

    private final List<List<String>> config;
    private final int rows;
    private final int columns;

    public Hierarchy(String hierarchyConfigPath) throws IOException {
        config = CsvReader.readCsv(hierarchyConfigPath);
        rows = config.size();
        columns = config.get(0).size();
    }

    public String generalize(String value) {
        for (int r = 0; r < rows-1; ++r) {
            for (int c = 0; c < columns; ++c) {
                if (config.get(r).get(c).equals(value)) {
                    return config.get(r+1).get(c);
                }
            }
        }

        return null;
    }
}
