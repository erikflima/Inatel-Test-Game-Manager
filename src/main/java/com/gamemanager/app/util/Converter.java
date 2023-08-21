package com.gamemanager.app.util;
import org.springframework.stereotype.Component;
import com.gamemanager.app.dtos.GameDTO;
import com.gamemanager.app.entities.Game;

@Component
public class Converter {

	public static Game convertGameDTOToGame( GameDTO gameDTO ){

		Game game = new Game();
		game.setName( gameDTO.getName() );
		game.setPublisherId( gameDTO.getPublisherId() );		
		game.setTimePlayed( gameDTO.getTimePlayed() );
		
		return game;
	}
	
}