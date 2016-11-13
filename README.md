# akka-log-processor

Sample Log Processor implemented in Java using Akka (Actor model based concurrency)

To run, first make a directory **logs/**

Include any amount of log files inside it

Then run

```
make all
```

Alternatively, you can edit makefile to pass any directory with log files inside as an argument to the compiled jar

```
java -jar target/m800-1.0-jar-with-dependencies.jar DIRECTORY-CONTAINING-LOG-FILES/
```
