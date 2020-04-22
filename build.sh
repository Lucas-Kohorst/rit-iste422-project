#!/bin/sh
mkdir build
javac -cp lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar -d build src/main/java/*.java
javac -cp lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar:build -d build src/test/java/*.java
java -cp .:lib/junit-4.12.jar:build:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore EdgeConnectorTest
# java -cp build RunEdgeConvert 

# Generate JAR File
echo "Main-Class: RunEdgeConvert" > Manifest.txt
jar cvfm dragon.jar Manifest.txt -C  build/  .
# java -jar dragon.jar
