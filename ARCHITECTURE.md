# Go

## Repository structure

- Main folder (this folder): contains the files relevant for the whole project. For example the Gradle-wrapper files, the .gitignore, and this readme.
- api/: contains the files for the API or service layer of your application.
- api/src/main/java/go/api: contains the web endpoints.
- api/src/main/java/go/api/models: contains the web endpoints.
- domain/: contains the files that model the business domain (game rules). 
- client/: contains the client (front-end)

## Prerequisites
To run Go website, the following needs to be installed:
- the Java Development Kit, with a correctly set JAVA_HOME local variable
- Node, to run the website
- Optionally Gradle up to date with your Java installation, to run the Java server which handles the domain logic.

## Structure
The webpage and client are in the './client/' folder. In './client/src/' are the typescript and css files which generate the webpage and the gameState.ts file, which contains the objects which are used in communication with the java application which runs the domain logic. In subfolders of './client/src/' are the typescript files which generate different pages on the website, such as the actual game application, the About page, the end of Game page and the page which starts a new game.

In the './api/' folder, we write the part of the application which is responsible for the communication between the webpage and the java package which is responsible for the game logic.
in './api/src/main/java/go/' we have the necessary Java files. The App.java file starts the server which listens to requests from the webpage. Individual files in './api/src/main/java/go/api'are responsible for the different requests which can be sent to the server, such as starting a game or playing a move on a gameboard. The './api/src/main/java/go/api/models/' folder contains the DTOs which can process the JSOn body of the HTTP requests which are made to the server.

Then, in the './domain/' map, we write the code which actually handles the game logic. The public class GoImpl in './domain/src/main/java/go/domain/' is the class with which the API layer communicates. It implements the requests which an outside entity could make, such as starting a new game or playing an intersection. The other files in this folder are the classes which implement the logic necessary for completing these actions. Tests for the code are in the './domain/src/test/java/go/domain/' folder.