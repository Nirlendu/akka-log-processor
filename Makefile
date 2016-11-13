all:
	mvn package
	java -jar target/m800-1.0-jar-with-dependencies.jar .logs/