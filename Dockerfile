FROM openjdk:8-jdk-alpine
ADD build/libs/spring-jms-activemq-0.0.1-SNAPSHOT.jar spring-jms-activemq.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-jms-activemq.jar"]