package com.gamemanager.app.dtos;
import java.time.LocalDate;
import java.util.Map;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class GameDTO {

    private String id;

    @NotBlank(message = "publisherId cannot be null or empty.")
    private String publisherId;

    @NotBlank(message = "name cannot be null or empty.")
    private String name;

    @Valid
    @NotEmpty(message = "timePlayed cannot be null or empty.")
    private Map<LocalDate, Integer> timePlayed;

	public GameDTO() {
	}
	
	//------------------------------//
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    
    public Map<LocalDate, Integer> getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed( Map<LocalDate, Integer> timePlayed ) {
        this.timePlayed = timePlayed;
    }
    
}