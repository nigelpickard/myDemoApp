Author: Nigel Pickard
Date: 02/25/2017

About the development:
Source Code control is Git, using github.  Build tool is Maven.
Overall the development was done on Intellij, using the Spring framework with ActiveMQ JMS and a MySQL backend database.
Running the code was done by utilizing Docker containers.

Overview:
The idea here is to utilize Docker to provide application and database services, running as logically separate services on the local machine.
With the application and datawarehouse subscribing to a common topic.

To allow for multiple databases and services, each database and service are allocated different ports.  Further, we need to associate each
database to each service.  The docker containers have been named to help identification, e.g.:

C:\Users\npickard\Documents\personal\workspace\myDemoProject\myDemoApp>docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
eb9a1a80c722        java:8u40           "java -jar /docker..."   6 seconds ago       Up 5 seconds        0.0.0.0:8081->8080/tcp   myDemo-DataWarehouse-8081-Db-3307
4916c8c02155        mysql:5.6.32        "docker-entrypoint..."   17 seconds ago      Up 16 seconds       0.0.0.0:3307->3306/tcp   myDemo-MySQL-3307
af6908816b10        java:8u40           "java -jar /docker..."   18 seconds ago      Up 18 seconds       0.0.0.0:8080->8080/tcp   myDemo-App-8080-Db-3306
7eb29552b2ff        mysql:5.6.32        "docker-entrypoint..."   30 seconds ago      Up 29 seconds       0.0.0.0:3306->3306/tcp   myDemo-MySQL-3306

Note the use of the name to describe the service and port, as well as the db and port the service uses.



