wikipedia-snapshots-analyzer
============================

To build a standalone executable:

mvn clean package assembly:single

Run with:
java -jar target/wikipedia-snapshot-analyzer-0.0.1-SNAPSHOT-jar-with-dependencies.jar snapshot.bz2
