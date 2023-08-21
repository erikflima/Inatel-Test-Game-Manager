package com.gamemanager.app.controllers;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamemanager.app.dtos.GameDTO;
import com.gamemanager.app.entities.Game;
import com.gamemanager.app.services.GameService;
import com.gamemanager.app.services.PublisherService;
import com.gamemanager.app.util.Converter;
import com.gamemanager.app.util.InputParameterValidator;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Game", description = "It manages game operations")
@RestController
@RequestMapping(produces="application/json")
public class GameController {
	
	@Autowired
	GameService gameService;

	@Autowired
	PublisherService publisherService;


	@PostMapping("/game") 
	public ResponseEntity<?> createAGame( @Valid @RequestBody GameDTO gameDTO, BindingResult bindingResult ){ 
		
		List<String> errors = new ArrayList<>();


		if ( bindingResult.hasErrors() ) {
			
			errors = InputParameterValidator.prepareValidationErrorMessagesList( bindingResult );
			
			return ResponseEntity.badRequest().body( errors );
		}


		if ( publisherService.PublisherIsNotValid( gameDTO.getPublisherId() ) ) {
						
			errors.add( "PublisherId is not valid" );
			
			return ResponseEntity.badRequest().body( errors );
		}


		Game game = Converter.convertGameDTOToGame( gameDTO );
				
		Game registeredGame = gameService.createAGame( game );

		URI uriToGetRegisteredGame = gameService.getUriToGetRegisteredGame( registeredGame.getId() );


		return ResponseEntity.created( uriToGetRegisteredGame ).body( registeredGame );
	}	


	@GetMapping("/game/findbyid/{id}") 
	public ResponseEntity<Game> findbyid( @PathVariable UUID id ){
		
		Optional<Game> optionalGame = gameService.findById( id );

		if ( !optionalGame.isPresent() ) {

			return ResponseEntity.noContent().build();	
		}

		return ResponseEntity.ok().body( optionalGame.get() );

	}


	@GetMapping("/game")
	public ResponseEntity< List<Game> > readGamesByPublisherId( @RequestParam(name = "publisherId", required = false) String publisherId ){
		       
		List<Game> games;
		
		
		if ( publisherId != null && !publisherId.trim().isEmpty() ) {
			
			games = gameService.findByPublisherId( publisherId );
						
		}else {
			
			games = gameService.findAll();
		}


		if ( games.isEmpty() ) {

			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().body( games );

	}


	@DeleteMapping(value = "/publishercache")
	public ResponseEntity<String> deletePublisherCache() {

		publisherService.clearCache();
		
		return ResponseEntity.ok().body( "Cache was deleted!" );
	}

}