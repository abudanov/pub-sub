PUBLISHER_JAR = publisher/build/libs/*.jar
SUBSCRIBER_JAR = subscriber/build/libs/*.jar

build: common-data/build.gradle publisher/build.gradle subscriber/build.gradle
	rm -f ${PUBLISHER_JAR}
	rm -f ${SUBSCRIBER_JAR}
	gradle bootJar

run-subscriber: subscriber/build/libs/*.jar
	java -jar ${SUBSCRIBER_JAR}

run-publisher: publisher/build/libs/*.jar
	java -jar ${PUBLISHER_JAR}