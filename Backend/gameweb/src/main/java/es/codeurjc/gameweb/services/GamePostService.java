package es.codeurjc.gameweb.services;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
@Service
public class GamePostService {
    
    private ConcurrentMap<Long, Game> gamesPosted = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();

	public GamePostService() {        
		save(new Game("Stardew Valley", Genres.RPG,"¡Stardew Valley es un RPG de vida en el campo! Hereda la vieja granja de tu abuelo en Stardew Valley. Armado con herramientas de segunda mano y algo de dinero, te dispones a empezar tu nueva vida. ¿Podrás aprender a vivir de la tierra y a convertir esos campos de malezas en un hogar próspero? No va a ser fácil. Desde que Joja Corporation llegó a la ciudad, las antiguas formas de vivir han desaparecido. El centro comunitario, en su momento el centro de actividad más activo de la ciudad, se encuentra ahora en ruinas. Pero el valle se encuentra lleno de oportunidades. ¡Con un poco de dedicación, podrás ser uno de los que devuelva Stardew Valley a la gloria!"));
		save(new Game("Skyrim",Genres.RPG,"La agitación se extiende por el Imperio de Tamriel. El soberano de Skyrim ha sido asesinado. A medida que surgen aspirantes al trono, se van formando alianzas. En mitad de este conflicto, resurge un antiguo mal mucho más peligroso: los dragones, olvidados hace tiempo entre oscuros pasajes de los antiguos pergaminos, han regresado a Tamriel. El futuro de Skyrim, incluso el del propio Imperio, pende de un hilo, mientras aguardan la llegada vaticinada del Sangre de dragón, un héroe dotado del poder de La Voz, el único que puede hacer frente a los dragones."));
		save(new Game("Hollow knight",Genres.Action,"Bajo la deteriorada ciudad de Petrópolis yace un antiguo reino en ruinas. A muchos les atrae la vida bajo la superficie y van en busca de riquezas, gloria o respuestas a viejos enigmas. Hollow Knight es una aventura de acción clásica en 2D ambientada en un vasto mundo interconectado. Explora cavernas tortuosas, ciudades antiguas y páramos mortales. Combate contra criaturas corrompidas, haz amistad con extraños insectos y resuelve los antiguos misterios que yacen en el corazón de reino."));
		save(new Game("dead by daylight",Genres.Horror,"Dead by Daylight es un juego de horror de multijugador (4 contra 1) en el que un jugador representa el rol del asesino despiadado y los 4 restantes juegan como supervivientes que intentan escapar de él para evitar ser capturados, torturados y asesinados.Los supervivientes juegan en tercera persona y tienen la ventaja de contar con una mejor percepción del entorno. El asesino juega en primera persona y está más enfocado en su presa."));
	
	}

	public Collection<Game> findAll() {
		return gamesPosted.values();
	}

	public Game findById(long id) {
		return gamesPosted.get(id);
	}

	public void save(Game game) {
		long id = nextId.getAndIncrement();

		game.setId(id);

		this.gamesPosted.put(id, game);
	}

	public void deleteById(long id) {
		this.gamesPosted.remove(id);
	}

}

