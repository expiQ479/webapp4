# webapp4
# Fase 0

## Nombre de la web
4DGames

## Descripción
Aplicación web de videojuegos donde los usuarios al registrarse pueden subscribirse a distintos juegos (los cuales son de varios géneros y tipos), y se les indicarán las actualizaciones, noticias y reviews con la media de nota. Depende del tipo de juego además se permite subir distintos tipos de posts u organizar grupos de juego. Por ejemplo, un juego multijugador competitivo permitirá que sus suscriptores creen grupos para jugar en equipo.
## Equipo de desarrollo 
| Nombre | Email | Usuario Github |
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
- Chat
- Posts(Engloba noticias, reviews, guias, etc.)
## Permisos de los usuarios
- Los usuarios pueden poner notas de los juegos, formar grupos y pueden visualizar el contenido de la página. 
- Los administradores pueden subir nuevo contenido a la plataforma.
- Los usuarios no registrados solo pueden visualizar contenido.
## Imágenes
- Los usuarios registrados podrán establecer una imagen de perfil.
- Los juegos tendrán una portada en la que se incluye el nombre y la imagen.
## Gráficos
Se dispondrá de un gráfico que muestra la media de la nota de las reviews de cada juego en el tiempo.
## Tecnología complementaria 
- Envío de correos a los usuarios.
## Algoritmo o consulta avanzada
- Se calcula la media de las reviews.
- Se creará una lista de recomendaciones para el usuario.

## Fase 1
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
