package es.codeurjc.gameweb.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import es.codeurjc.gameweb.models.Chat;
import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.repositories.ChatRepository;
import es.codeurjc.gameweb.repositories.GameRepository;

@Service
public class DBInitializer {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ChatRepository chatRepository;

    @PostConstruct
	public void init() throws IOException, URISyntaxException {
        //sample games of 4dgames
        
        Game g1 =new Game("Stardew Valley", Genres.RPG,"¡Stardew Valley es un RPG de vida en el campo! Hereda la vieja granja de tu abuelo en Stardew Valley. Armado con herramientas de segunda mano y algo de dinero, te dispones a empezar tu nueva vida. ¿Podrás aprender a vivir de la tierra y a convertir esos campos de malezas en un hogar próspero? No va a ser fácil. Desde que Joja Corporation llegó a la ciudad, las antiguas formas de vivir han desaparecido. El centro comunitario, en su momento el centro de actividad más activo de la ciudad, se encuentra ahora en ruinas. Pero el valle se encuentra lleno de oportunidades. ¡Con un poco de dedicación, podrás ser uno de los que devuelva Stardew Valley a la gloria!");
		setGameImage(g1, "/sample_images/image-0.jpg");
        Chat chat1 = new Chat();
        g1.setChat(chat1);
        gameRepository.save(g1);

        Game g2 =new Game("Skyrim",Genres.RPG,"La agitación se extiende por el Imperio de Tamriel. El soberano de Skyrim ha sido asesinado. A medida que surgen aspirantes al trono, se van formando alianzas. En mitad de este conflicto, resurge un antiguo mal mucho más peligroso: los dragones, olvidados hace tiempo entre oscuros pasajes de los antiguos pergaminos, han regresado a Tamriel. El futuro de Skyrim, incluso el del propio Imperio, pende de un hilo, mientras aguardan la llegada vaticinada del Sangre de dragón, un héroe dotado del poder de La Voz, el único que puede hacer frente a los dragones.");
		setGameImage(g2, "/sample_images/image-1.jpg");
        Chat chat2 = new Chat();
        g2.setChat(chat2);
        gameRepository.save(g2);

        /*
        Game g3 =new Game("Hollow knight",Genres.Action,"Bajo la deteriorada ciudad de Petrópolis yace un antiguo reino en ruinas. A muchos les atrae la vida bajo la superficie y van en busca de riquezas, gloria o respuestas a viejos enigmas. Hollow Knight es una aventura de acción clásica en 2D ambientada en un vasto mundo interconectado. Explora cavernas tortuosas, ciudades antiguas y páramos mortales. Combate contra criaturas corrompidas, haz amistad con extraños insectos y resuelve los antiguos misterios que yacen en el corazón de reino.");
		setGameImage(g3, "/sample_images/image-2.jpg");
        Chat chat3 = new Chat();
        chatRepository.save(chat3);
        g1.setChat(chat3);
        gameRepository.save(g3);
        Game g4 =new Game("dead by daylight",Genres.Horror,"Dead by Daylight es un juego de horror de multijugador (4 contra 1) en el que un jugador representa el rol del asesino despiadado y los 4 restantes juegan como supervivientes que intentan escapar de él para evitar ser capturados, torturados y asesinados.Los supervivientes juegan en tercera persona y tienen la ventaja de contar con una mejor percepción del entorno. El asesino juega en primera persona y está más enfocado en su presa.");
        Chat chat4 = new Chat();
        chatRepository.save(chat4);
        g1.setChat(chat4);
        gameRepository.save(g4);
        setGameImage(g4, "/sample_images/image-3.jpg");
        gameRepository.save(g4);
        */
    }

    public void setGameImage(Game game, String classpathResource) throws IOException {
        game.setImage(true);
        Resource image = (Resource) new ClassPathResource(classpathResource);
        game.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));

    }

}
