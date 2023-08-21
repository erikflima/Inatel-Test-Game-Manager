package com.gamemanager.app.services;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gamemanager.app.entities.Game;
import com.gamemanager.app.repositories.GameRepository;

@Service
public class GameService {

	private static final Logger log = LoggerFactory.getLogger( GameService.class);
	 
	@Autowired
	private GameRepository gameRepository;
	
	
	public GameService() {
	}


	public Game createAGame( Game game ) {

		log.info("Registering a game: ", game.getName() );
		
		Game registeredGame = gameRepository.save( game );
		
		return registeredGame;
	}


	public Optional<Game> findById( UUID id ) {

		log.info("Finding a game by id: ", id );
		
		Optional<Game> optionalGame = gameRepository.findById(id);
		
		return optionalGame;
	}


	public List<Game> findAll() {
		
		log.info("Finding all games.");
		
		List<Game> games = gameRepository.findAll();		
		
		return games;
	}


	public List<Game> findByPublisherId( String publisherId ) {
		
		log.info("Finding by publisher Id.");
		
		List<Game> games = gameRepository.findByPublisherId( publisherId );		
		
		return games;
	}


	public URI getUriToGetRegisteredGame(UUID id) {

		URI uriToGetRegisteredGame = ServletUriComponentsBuilder.fromCurrentContextPath()
												                .path("/game/findbyid/{id}")
												                .buildAndExpand( id )
												                .toUri();

		return uriToGetRegisteredGame;
	}

}