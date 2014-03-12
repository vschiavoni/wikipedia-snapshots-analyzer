wikipedia-snapshots-analyzer
============================


To build :
```bash
mvn clean package assembly:single
```

Run an infinispan node with: 
```bash
java -jar target/wikipedia-snapshot-analyzer-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

Run a client with:
```bash
java -DISPN_CLIENT -jar target/wikipedia-snapshot-analyzer-0.0.1-SNAPSHOT-jar-with-dependencies.jar path/to/snapshot.xml
```