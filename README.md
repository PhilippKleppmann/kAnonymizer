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

There are many potential refinements to the algorithm, for example giving a preference for which column is generalized or when to drop rows.

Also, it would be nice to mark certain columns of a table as quasi-identifiers, and run the k-anonymization algorithm based only on these.

#### Comments

The implementation of the data storage in `FrequencyList` doesn't seem optimal, as the data is copied at every generalization step.
Can we be more space-efficient?
Yes, we can modify the data in place.
But then `Multiset::count` (used to determine the end of the generalization process) returns wrong results, as generalization makes some entries equal that were distinct before.
A new implementation of `Multiset::count` would have to iterate over the multiset to get new counts.
So the space-efficiency comes at the cost of computational efficiency.
