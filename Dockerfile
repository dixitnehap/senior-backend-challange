FROM openjdk:11.0-jdk
MAINTAINER dixitnehap
WORKDIR /opt/application
ARG JAR_FILE=target/challange-1.0.0.jar
COPY ${JAR_FILE} challange.jar

EXPOSE 8080/tcp

ENTRYPOINT ["java", "-jar", "challange.jar"]