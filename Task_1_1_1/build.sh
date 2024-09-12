javac -d bin -cp /home/kirill/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-api/5.10.0/2fe4ba3d31d5067878e468c96aa039005a9134d3/junit-jupiter-api-5.10.0.jar:/home/kirill/.gradle/caches/modules-2/files-2.1/org.junit.jupiter/junit-jupiter-engine/5.10.0/90587932d718fc51a48112d33045a18476c542ad/junit-jupiter-engine-5.10.0.jar src/main/java/ru/nsu/kozlov/Main.java src/test/java/ru/nsu/kozlov/MainTest.java
touch Manifest.txt
echo -e "Manifest-Version: 1.0\nMain-Class: ru.nsu.kozlov.Main\nclass-Path: bin/ru/nsu/kozlov" > Manifest.txt
jar cfm MyLab.jar Manifest.txt -C bin .
java -jar MyLab.jar
javadoc -d bin/docs -sourcepath src/main/java ru.nsu.kozlov

echo "Pobeda!"