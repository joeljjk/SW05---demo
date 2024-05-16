[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/from-referrer/) 

### DevOpsStarter

DevOps Prototype
* Understanding DevOps by doing it :-)

## Required Tools
* Git (https://git-scm.com/)
* Node and NPM (https://nodejs.org/)
* Java SDK 21 (https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
* Gradle 8.6 (optional, Gradle Wrapper may be used)

## Installation
* Clone repository with Git
* Open a console/terminal window and change directory to frontend:
```
cd frontend
npm install
npm start
```

## Deployment
* Open a console/terminal window and change directory to backend:
```
cd backend
gradle bootRun or gradlew bootRun or ./gradlew bootRun
```
* You may check the backend is running by opening http://localhost:8080/services/ping in a web browser
* You may check the frontend is running by opening http://localhost:4200/ in a web browser

## Options
Some options may be set by using a environment variable or Java system property before executing the commands above:
* PORT=xyz serves on a different port
