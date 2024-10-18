package gameofthrones.proyecto.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Characters{

	@JsonProperty("Characters")
	private List<CharactersItem> characters;

	public Characters() {
	}

	public List<CharactersItem> getCharacters(){
		return characters;
	}
}