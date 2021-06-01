# README
This file explains how to create the development environment, the database and how to launch and use the program.

- Program: 
This program inserts data from an API into a database. The API of this program is a json type file where there is data updated in real time. In the case of this program we are talking about Sensors and Centraline. The data received are the values of the Sensors contained in the Centraline.
The call of this program is made every hour and only the last Sensor value is entered in the database.

- Database: 
To create the database you need to download an RDBMS Workbench, for example MySQL Workbench, and run the scripts present in the dbScript folder.

- JavaProject: 
For java project you need to download an IDE, for example VSCode, and import the CentralineProject project.

The program works if the following data are entered in the config.jason.sample file: the API you want to use and the information of the DB created.

- lib: 
This folder contains the java libraries to use. The first is used to manage json files, the second is used to create a link between java and dababase.


javac -sourcepath src -cp lib/json-20210307.jar:lib/mysql-connector-java-8.0.25.jar -d classes src/**/*.java

java -cp lib/json-20210307.jar:lib/mysql-connector-java-8.0.25.jar:classes main.Main