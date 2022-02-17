FROM adoptopenjdk:8-jre-openj9
LABEL version="1.0.0"
COPY target/testautomation-esb-app-1.0.0-SNAPSHOT.jar testautomation-esb-app-1.0.0.jar
ENTRYPOINT ["java","-jar","/testautomation-esb-app-1.0.0.jar"]