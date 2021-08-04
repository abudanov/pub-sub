SUBSCRIBER_JAR = subscriber/build/libs/*.jar
PUBLISHER_JAR = publisher/build/libs/*.jar

build: settings.gradle common-data/build.gradle publisher/build.gradle subscriber/build.gradle
	gradle bootJar

run-subscriber: subscriber/build/libs/*.jar
	java -jar ${SUBSCRIBER_JAR}

run-publisher: publisher/build/libs/*.jar
	java -jar ${PUBLISHER_JAR}

tests: publisher/build.gradle subscriber/build.gradle
	gradle test

clean: settings.gradle common-data/build.gradle publisher/build.gradle subscriber/build.gradle