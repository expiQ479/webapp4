# webapp4

# Phase 0

## Web name
4DGames

## Description
Videogames web application where users, when registered, can subscribe to different games (all are sorted by genre), and they will be notified of any news, updates and reviews.
There's also a live chat for people to create play groups or ask questions and recieve answers from other users.
## Equipo de desarrollo 
| Name | Email | Github User |
| -- | -- | -- |
| Eduardo Sierra Martin       | e.sierram.2018@alumnos.urjc.es  | eduardosoy    |
| Enrique Carmona Blazquez    | e.carmonab.2018@alumnos.urjc.es | expiQ479      |
| Gabriel Fuentes Villasevil  | g.fuentes.2018@alumnos.urjc.es  | gfuentes2018  |
| Carlos Javier Hervás Ledesma| cj.hervas.2018@alumnos.urjc.es  | Carlitosh636  |
## Trello

https://trello.com/b/wcg6yyZU/daw-g4

## Entities
- Games
- Users 
- Chat
- Posts(News, reviews...)
## User permissions
- Any type of user can view the content, even if they are not registered.
- Registered users can review games (give a 1-5 score), chat and subscribe to games.
- Administrators can upload content and have regular permissions (registered user).
## Images
- Registered users can set a profile picture
- Games include a front picture and a caroussel in their specific page.
## Graphs
- The average review score will display in a graph.
## Third party services
- Notify users by email.
## Algorithms and advanced enquery
- An algorithm will determine which games should be recommended to the users. These will appear in a widget on some of the pages.

# Phase 1
### admin-updates.png
![](Capturas%204DGames/admin-updates.png)
This page is only available to administrators. It shows a list of all games in the database with their portrait, name, description, genre and three options: Edit game, Create post & Delete game
There's also a button to create a game on the upper left corner.

### add-game.png
![](Capturas%204DGames/add-game.png)
This page is only available to administrators. Here you can create a new game entity filling the fields. The new game will be visible in the admin-updates.html page.

### edit-game.png
![](Capturas%204DGames/edit-game.png)
This page is only available to administrators. It shows different fields of an existing game which allow for modifications. Any changes will be saved.

### create-posts.png
![](Capturas%204DGames/create-posts.png)
This page is only available to administrators. It shows a box with different fields to fill(Image, title, text content, type), which are taken into a function to create a new Post entity.

### edit-posts.png
![](Capturas%204DGames/edit-posts.png)
This page is only available to administrators. It shows different fields of an existing post which allow for modifications. Any changes will be saved.

### game-page.png
![](Capturas%204DGames/game-page.png)
This page is available to any type of user. It shows all the data from an individual game, and for registered users it includes a subscription/unsubscription button, chat and rate game button.

### expanded-posts.png
![](Capturas%204DGames/expanded-posts.png)
This page is available to any type of user. This shows a post with all it's data, and a widget to the right with recommended games/best rated. Admins will be shown an Edit Post button.

### index.png
![](Capturas%204DGames/index.png)
This page is available to any type of user. This is the main page for the app, showing an image carousel, some games and a widget with reccomended games/best rated.

### list-games.png
![](Capturas%204DGames/list-posts.png)
This page is available to any type of user. This page shows a grid with all existing games, and a widget on the right to filter depending on game genre.

### list-posts.png
![](Capturas%204DGames/admin-updates.png)
This page is available to any type of user. This page shows a list of a determined type of post, and at the right the recommended/best rated games widget. When clicking on the post image it redirects to expandedPost.png

### login.png
![](Capturas%204DGames/login.png)
This page is available when not logged in. This page shows a box with two fields, name and password. When introducing existing credentials it logs the determined user. 

### profile.png
![](Capturas%204DGames/profile.png)
This page is available to logged users. This page shows the user's profile, with their profile pic, name, password, button to user's subscriptions, log out button and options to edit your information (pic, username, password...)

### register.png
![](Capturas%204DGames/register.png)
This page is available when not logged in. This page shows a box with four fields. Introducing data will create a new account. Password must be confirmed before register.

### statistics.png
![](Capturas%204DGames/statistics.png)
This page is available to any type of user. This page shows the average rate of a game & a chart showing how many people have voted a determined amount of stars (points)

### subscriptions.png
![](Capturas%204DGames/subscriptions.png)
This page is available to logged users. This page shows a list of games subbed by the user, showcasing image, title, description and genre. You can unsub with a button at the right.

### success-page.png
![](Capturas%204DGames/success-page.png)
This page is available to logged users. This page shows when the user carries out a succesful operation (create post, edit game, subscribe...)


## Build instructions
Disclaimer: The Project is running on version 11 of Java and Visual Studio Code.
---
In order to construct the Project, we used a target folder with the .jar file. To create it, you must open the Project in Visual Studio Code and get into the terminal.
Once there, navigate to the .mvn\wrapper folder introducing the command:
```cmd
cd Backend\gameweb\.mvn\wrapper
```
When the console prompt indicates you are inside the wrapper folder, introduce:
```cmd
& "c:\Users\Usuario\Git\webapp4\Backend\gameweb\mvnw.cmd" package -f "c:\Users\Usuario\Git\webapp4\Backend\gameweb\pom.xml"
```
This will generate the .jar file, along the target folder
With your shell of choice, navigate to the location of the .jar file and once there execute:
```cmd
java –jar {your_jar_filename_here.jar}
```
You can include options such as --server.port to indicate which port the app will use (this overwrites the application.properties settings)
If everything is correct, it will compile and allow for execution.

# Phase 2

## Data base diagram
![](Capturas%204DGames/DBDiagram.PNG)

# Team participation
## Gabriel Fuentes Villasevil

### Completed tasks description

The complete implementation of the stadistics in the app, catching the valorations of the users and showing them, the implementation of the chat inside the games and all the search work to fill the page with examples and the gamepage template with all its functions. Also help in security.

### Focussed files:
 | Number | Description | Focused on Files |
 | ------ | ------------| -----------------|
 | #1 | Show real example information in the games. | [DBInitializer.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/f00aa0ce40ea01f1a48f6b4c0496abe1875dee95/Backend/gameweb/src/main/java/es/codeurjc/gameweb/services/DBInitializer.java)|
 | #2 | The stadistics (a bar diagram of the valorations of the game) and all the functions behind the stadistics| [GamePageController.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/f00aa0ce40ea01f1a48f6b4c0496abe1875dee95/Backend/gameweb/src/main/java/es/codeurjc/gameweb/controllers/GamePageController.java) |
 | #3 | The Chat inside each game, with all its implementation | [GamePageController.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/f00aa0ce40ea01f1a48f6b4c0496abe1875dee95/Backend/gameweb/src/main/java/es/codeurjc/gameweb/controllers/GamePageController.java) |
 | #4 | Maked that all in the game template works properly| [gamePage.html](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/f00aa0ce40ea01f1a48f6b4c0496abe1875dee95/Backend/gameweb/src/main/resources/templates/gamePage.html) |
 | #5 | Creation of Message and Chat models| [Message.java ](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/f00aa0ce40ea01f1a48f6b4c0496abe1875dee95/Backend/gameweb/src/main/java/es/codeurjc/gameweb/models/Message.java)[ Chat.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/f00aa0ce40ea01f1a48f6b4c0496abe1875dee95/Backend/gameweb/src/main/java/es/codeurjc/gameweb/models/Chat.java) |

### Relevants commits:
 | Number | Description | Commit |
 | ------ | ----------- | ------ |
 | #1 | creation of log in and register controllers and make that shows gamepage different takeing in consideration if the user is logged or not| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/ada07298f5b1737a136ec3557f0221823aa42d1e)|
 | #2 | creation of messages and chat structures | [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/1e07c6a7cbcbe43d3afc65e6ba34660e5a512be2)|
 | #3 | One of the last changes to the game and chat implementations, and implements that each game have a different page instead of all the same, with mustache| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/8971779a7b7f2af4549868de0ad89e3614ad896e)|
 | #4 | Last change to the valoration implementation, finishing it| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/6c8133c96325587dd41b88f9e28407b1ac2ce9ee)|
 | #5 | Make that the application have real example info instead of just a "Lorem ipsums"| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/6c4d8ede1134bdb977cc32f103958e6afe86d43e)|
 
 ## Carlos Javier Hervás Ledesma

### Completed tasks description
Bulk of my work was the entity, upload, editing and showcase of the Post entity, creation of two algorithms, shape the basic models for the entities and some secondary functions, such as subscription/unsubscription to a Game entity. Also I worked on Load More button (with AJAX implementation) & general bugfixing.

### Focussed files:
 | Number | Description | Focused on Files |
 | ------ | ------------| -----------------|
 | #1 | Controller for posts. Includes creation, edit, and access to post related pages.| [PostsController.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/controllers/PostsController.java)|
 | #2 | Controller for games, worked on sub & unsub buttons.| [GamePageController.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/controllers/GamePageController.java)|
 | #3 | Post model. Holds all post info with getters/setters.| [Post.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/models/Post.java)|
 | #4 | Algorithm service. Includes both recommended & best rated algorithms, along with any auxiliary methods.| [AlgorithmService.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/services/AlgorithmService.java)|
 | #5 | Game service. Worked on some auxiliary functions to filter games depending on determined factors| [GameService.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/services/GameService.java)|
### Relevenats commits:
 | Number | Description | Commit |
 | ------ | ----------- | ------ |
 | #1 | Subscription button & function for game entities.| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/78e2ce614686c4b313b51635b92e4e1941dc7fc4)|
 | #2 | Recommended algorithm. Depending on the user's subscriptions it recommends different games| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/377ad4a2f72284ea87b9bd7dfa56b0cb6f63ae82)|
 | #3 | Post creation function with image.| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/b86744df818014da08c30496f5457e69bacdf34a)|
 | #4 | Edit posts function.| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/620570a8971a740e6072652ad3614cc3a8e7c0f7)|
 | #5 | Custom error pages (recognizes error 404 & 500, else it returns generic error page)| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/52344ef7942789fac032b57741bdf3658e2b7e8a)|
 
 ## Enrique Carmona Blazquez

### Completed tasks description
My job in this project, was essentially, create the entity User and all the methods that involve the user, with this i mean that i have done the register of the user, the log in, the suscription to the games and all the functions of the profile and the page with all the suscriptions. At the same time i colaborated with Eduardo Sierra in the process of creation of the security, i colaborated too with Gabriel Fuentes in the creation of the chat and also help fixing some errors during the creation of the project.

### Focussed files:
 | Number | Description | Focused on Files |
 | ------ | ------------| -----------------|
 | #1 | Controller for the profile: Image of the user, change image of the user, change password and name, log out and nav to suscription list.| [ProfileController.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/controllers/ProfileController.java)|
 | #2 | Controller for the register: Register of the user, error if the user already exist and if the password confirmation its correct.| [RegisterController.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/controllers/RegisterController.java)|
 | #3 | Model User: Its the entity user with all the atributes methods relevant to the data of the user.| [User.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/models/User.java)|
 | #4 | Service of the User: its refers to the methods for manipulate the data store in the DB | [UserService.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/services/UserService.java)|
 | #5 | Its the secury of the app web, this file determinate the restrictions for the users and permit the logout and login| [WebSecurityConfig.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/security/WebSecurityConfig.java)|
### Relevenats commits:
 | Number | Description | Commit |
 | ------ | ----------- | ------ |
 | #1 | Update security now you cant create a user with the same name, or change your nam to other user name that already exist.| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/tree/61f14d0930940bac692aca280a1dd0fa7c356583)|
 | #2 | One of the Security updates| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/d634c01a78ff7d692f3ecbe008a0a1d820fa651c)|
 | #3 | One of the lastest updates in the suscriptions.| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/30f3f18997da860034b604a50c5d22a1fb7177d9)|
 | #4 | Creatión of the service of the user and implementation of al refere to the user DB.| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/36c0b919e8d19ec1c56cc8b531ffccaa609061d7)|
 | #5 | One of the implementation of the profile, more specifictly the operation of change the image of the user| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/100216de2e7f75e2b000c7590739683bd6aae782)|
 
 ## Eduardo Sierra Martin

### Completed tasks description
Most of the actions of the administration staff, such as the uploading of games and covers. That games can be edited and deleted with a dedicated interface. Supervise the design of the entire website and security.

### Focussed files:
 | Number | Description | Focused on Files |
 | ------ | ------------| -----------------|
 | #1 | the controller of all the actions that administrators can execute on their dedicated pages| [AdminUpdatesController.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/controllers/AdminUpdatesController.java)|
 | #2 | show all games on the website in a list and filter functionality| [GameListController.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/controllers/GamesListController.java) |
 | #3 | "Recommended games" algorithm implemented as a service usable by drivers | [AlgorithmService.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/services/AlgorithmService.java) |
 | #4 | Initialize the databases with sample data| [DBInitializer.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/services/DBInitializer.java) |
 | #5 | It is the game as such, the basis of which the entire web application is composed| [Game.java](https://github.com/CodeURJC-DAW-2020-21/webapp4/blob/b82e1abd9732d6b630c7d8316d049c16cb3d7c20/Backend/gameweb/src/main/java/es/codeurjc/gameweb/models/Game.java) |

### Relevenats commits:
 | Number | Description | Commit |
 | ------ | ----------- | ------ |
 | #1 | code restructuring to add better quality| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/f8a859a986531c11d52d655f76e7efab49458023)|
 | #2 | it´s can edit, upload and remove games but in db| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/90c97350fa186e7046ae28d0c606c4468cdbd8d2)|
 | #3 | it´s can upload games as admins| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/6960378b7a2d184d1cd240ca323cf09c52542280)|
 | #4 | first almost full funcional DB in the aplication web| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/5463e644cadb4e71441a9d1f9db1afa8e96da27f)|
 | #5 | first security implementation| [commit](https://github.com/CodeURJC-DAW-2020-21/webapp4/commit/4e93beb5852e1ac222f75fe532ddd97a66409fc6)|
 

## Build instructions
Disclaimer: The Project is running on version 11 of Java and Visual Studio Code.
In order to construct the Project, we used a target folder with the .jar file. To create it, you must open the Project in Visual Studio Code and get into the terminal.
Once there, navigate to the .mvn\wrapper folder introducing the command:
```cmd
cd Backend\gameweb\.mvn\wrapper
```
When the console prompt indicates you are inside the wrapper folder, introduce:
```cmd
& "c:\Users\Usuario\Git\webapp4\Backend\gameweb\mvnw.cmd" package -f "c:\Users\Usuario\Git\webapp4\Backend\gameweb\pom.xml"
```
This will generate the .jar file, along the target folder
With your shell of choice, navigate to the location of the .jar file and once there execute:
```cmd
java –jar {your_jar_filename_here.jar}
```
You can include options such as --server.port to indicate which port the app will use (this overwrites the application.properties settings)
If everything is correct, it will compile and allow for execution.

# Phase 3

## Build instructions
In a Ubuntu system With docker installed and cloned this repository, execute the following commands:
1. docker run --rm -v "$PWD":/data -w /data maven mvn package 
2. docker-compose build
3. docker-compose up

To construct a new image it´s can use the script with name "create_image.sh"






