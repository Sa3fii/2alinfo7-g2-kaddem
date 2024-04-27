FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD target/kaddem-0.0.1-SNAPSHOT.jar kaddem-0.0.1-SNAPSHOT
ENTRYPOINT ["java","-jar","/kaddem-0.0.1-SNAPSHOT.jar"]