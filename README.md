# Testautomation Spring Boot Camel Project

This example demonstrates how to write test cases for esb app.

IMPORTANT: This quickstart can run in one modes: standalone on your machine and on Kubernetes / OpenShift Cluster


## Running the quickstart standalone on your machine through CLI

To run this quickstart as a standalone project on your local machine:

* Build the project:

    $ mvn clean package
    
* Run the service:

    $ mvn spring-boot:run

* See the messages sent by Camel.

docker build -t esb/testautomation-esb-app .

docker run -p 8080:8080 esb/testautomation-esb-app