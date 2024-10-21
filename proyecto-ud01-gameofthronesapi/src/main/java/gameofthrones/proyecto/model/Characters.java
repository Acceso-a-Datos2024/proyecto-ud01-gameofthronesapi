package gameofthrones.proyecto.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.Marshaller;

@XmlRootElement(name = "characters") // Esta anotación es necesaria
public class Characters{

	private List<CharactersItem> characters;

	@XmlElement(name = "character")
	public List<CharactersItem> getCharacters(){

		return characters;
	}

	public void setCharacters(List<CharactersItem> characters) {
		this.characters = characters;
	}
}