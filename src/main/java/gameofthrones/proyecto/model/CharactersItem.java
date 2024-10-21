package gameofthrones.proyecto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.Marshaller;

import java.io.Serializable;

@XmlRootElement(name = "character")
public class CharactersItem implements Serializable {

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("image")
	private String image;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("fullName")
	private String fullName;

	@JsonProperty("id")
	private int id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("family")
	private String family;

	@XmlElement
	public String getFirstName(){
		return firstName;
	}

	@XmlElement
	public String getLastName(){
		return lastName;
	}

	@XmlElement
	public String getImage(){
		return image;
	}

	@XmlElement
	public String getImageUrl(){
		return imageUrl;
	}

	@XmlElement
	public String getFullName(){
		return fullName;
	}

	@XmlElement
	public int getId(){
		return id;
	}

	@XmlElement
	public String getTitle(){
		return title;
	}

	@XmlElement
	public String getFamily(){
		return family;
	}

	@Override
	public String toString() {
		return id +  " " + firstName + " " + lastName + " " + image + " " + imageUrl + " " + fullName + " " + title + " " + family;
	}
}