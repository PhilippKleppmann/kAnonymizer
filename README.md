#### K-Anonymization

This project is a k-anonymization tool implementing a version of the [DataFly algorithm](https://en.wikipedia.org/wiki/Datafly_algorithm).

The dataset to be anonymized must be stored in a csv file `<name>.csv`. All columns are assumed to be quasi-identifiers. The result of the anonymization is written to a new file `<name>.csv.anon`.

The algorithm requires the following parameters:
* the anonymity level `k`,
* for each column in the data a hierarchy describing its generalization rules, also saved in a csv file.


#### Try it yourself

This project contains an extract `adults.csv` of the [adult dataset](https://archive.ics.uci.edu/ml/datasets/Adult) for testing.

To build the project, execute `./gradlew assemble` in the project root. In order to anonymize the given example dataset, execute `KAnonymizationIntegrationTest.anonymizeAdults()`.


#### Future projects

The implementation of the data storage in `FrequencyList` is not optimal. Currently, the list is copied when generalizing columns or dropping small equivalence classes. This is because we are modifying a collection while iterating over it. Implementing a custom data structure that allows modifying/deleting fields in place would make the algorithm more space-efficient.

Additionally, there are many potential refinements to the algorithm, for example giving a preference for which column is generalized or when to drop rows.

Also, it would be nice to mark certain columns of a table as quasi-identifiers, and run the k-anonymization algorithm based only on these.