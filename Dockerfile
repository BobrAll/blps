FROM openjdk:17
LABEL authors="Bobrus Alexander"

COPY . /java
WORKDIR /java

ENTRYPOINT ["java", "-jar", "target/blps_lab1-0.0.1-SNAPSHOT.jar"]