wikipedia-snapshots-analyzer
============================

To use the binary executable:
````bash
java -jar bin/wikipedia-snapshot-analyzer-0.0.1-SNAPSHOT-jar-with-dependencies.jar path/to/snapshot.xml
```

To build from source :
```bash
mvn clean package assembly:single
```

Run with:
```bash
java -jar target/wikipedia-snapshot-analyzer-0.0.1-SNAPSHOT-jar-with-dependencies.jar path/to/snapshot.xml
```
