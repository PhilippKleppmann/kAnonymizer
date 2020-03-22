This project is a k-anonymization tool implementing a version of the [DataFly algorithm](https://en.wikipedia.org/wiki/Datafly_algorithm).

The dataset to be anonymized must be stored in a csv file `<name>.csv`. All columns are assumed to be quasi-identifiers. The result of the anonymization is written to a new file `<name>.csv.anon`.

The algorithm requires the following parameters:
* the anonymity level `k`,
* for each column in the data a hierarchy describing its generalization rules, also saved in a csv file.

This project contains an extract `adults.csv` of the [adult dataset](https://archive.ics.uci.edu/ml/datasets/Adult) for testing.

To build the project, execute `./gradlew assemble` in the project root. In order to anonymize the given example dataset, execute `KAnonymizationIntegrationTest.anonymizeAdults()`