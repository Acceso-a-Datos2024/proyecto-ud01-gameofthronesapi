package gameofthrones.proyecto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CharactersItem{

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

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getImage(){
		return image;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public String getFullName(){
		return fullName;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getFamily(){
		return family;
	}
}