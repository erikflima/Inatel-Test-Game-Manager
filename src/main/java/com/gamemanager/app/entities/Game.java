package com.gamemanager.app.entities;
import java.io.Serializable;
import java.util.UUID;
import java.util.Map;
import java.time.LocalDate;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;

@Entity
@Table(name = "game")
public class Game implements Serializable{
	
	private static final long serialVersionUID = 2576981550405079774L;

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "publisher_id")
    private String publisherId;
    
    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable( name = "time_played", joinColumns = @JoinColumn(name = "game_id") )
    @MapKeyColumn(name = "play_date")
    @Column(name = "hours_played")
    private Map<LocalDate, Integer> timePlayed;

    
	public Game() {
	}
	
	//------------------------------//
	
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public void setTimePlayed(Map<LocalDate, Integer> timePlayed) {
        this.timePlayed = timePlayed;
    }
    
}