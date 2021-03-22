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

## Entidades
- Juegos
- Usuarios 
- Grupos 
- Reviews
- Noticias
## Permisos de los usuarios
- Los usuarios pueden subir juegos, poner notas de los juegos y formar grupos. 
- Los administradores pueden moderar qué se sube a la plataforma además de los permisos de usuario estándar.
- Los usuarios no registrados solo pueden visualizar contenido.
## Imágenes
- Los usuarios registrados podrán establecer una imagen de perfil.
- Los juegos tendrán una portada en la que se incluye el nombre y la imagen.
## Gráficos
Se dispondrá de un gráfico que muestra la media de la nota de las reviews de cada juego en el tiempo
## Tecnología complementaria 
- Envío de correos a los usuarios.
- Creación de usuario y login con Google, Facebook o Twitter
## Algoritmo o consulta avanzada
- Se calcula el rendimiento medio del equipo en base a partidas ganadas y perdidas.
- Se calcula la media de las reviews
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
![](Capturas%204DGames/Home.png)
Homepage of our website (Logged in version). Includes an image caroussel of games, multiple posts below (randomized every time), and a recommended games tab on the right.

![](Capturas%204DGames/HomeNonLoged.png)
Same page as before, but the unregistered version. It includes a "log in" and "register" buttons on the upper right corner.

![](Capturas%204DGames/IniciarSesion.png)
Sign in page. You need the user's ID or email and a password.

![](Capturas%204DGames/JuegoPage.png)
Single game page (Logged in version). Includes a photo caroussel, description, subscription button for notifications, chat, and buttons that link to a list of posts (guides, news, updates).

![](Capturas%204DGames/JuegoPageNonLoged.png)
Single game page, unregistered version. It does not include the chat nor the subscribe button.

![](Capturas%204DGames/Juegos.png)
Game list page (Logged in version). You can search games by genre, and each one gives you a list of games on that genre. You can see the title and click on them to view the detailed single game page.

![](Capturas%204DGames/JuegosNonLoged.png)
Game list page, unsigned version

![](Capturas%204DGames/ListaPosts.png)
Post list (Logged in version), this shows a list of posts of a certain type (depends on what button was pressed on single game page), related to the respective game. Each is a button with image, title and short description. Now there's a button to load more results, but it will generate dinamically more results with the infinite scrolling technique in the future.

![](Capturas%204DGames/ListaPostsNonLoged.png)
Post list, unsigned version

![](Capturas%204DGames/Perfil.png)
Profile page, including user data and settings (change name, password, list of subscriptions and log out).

![](Capturas%204DGames/PostPage.png)
Detailed post page (Logged in version), including title, author, posting date, image and body. Right widget shows more recommended games.

![](Capturas%204DGames/PostPageNonLoged.png)
Detailed post page, unsigned version.

![](Capturas%204DGames/Registrarse.png)
Register page. You must introduce an ID, email, password and a confirmation.

![](Capturas%204DGames/Suscripciones.png)
Subscription list, accesible from the profile page. It shows a list with an image, title, description, genre and the option to unsubscribe.

## Navegation Diagram
![](Navegaciones%204DGames.png)
# Phase 2
## Class and template diagram
![](Capturas%204DGames/diagramaClases.png)

## Data base diagram
![](Capturas%204DGames/DBDiagram.PNG)

# Team participation
## Gabriel Fuentes Villasevil

### Completed tasks description
example example example example example

### Focussed files:
 | Number | Description | Focused on Files |
 | ---- | ------------- | ------------ |
 | #1 | example of description| [GamePage.java](https://es.wikipedia.org/wiki/Leche)|
 
### Relevenats commits:
 | Number | Description | Commit |
 | ---- | ------------- | ------------ |
 | #1 | example| [commit](https://)|
 








