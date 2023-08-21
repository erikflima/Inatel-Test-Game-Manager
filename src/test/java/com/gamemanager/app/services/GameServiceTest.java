package com.gamemanager.app.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.gamemanager.app.entities.Game;
import com.gamemanager.app.repositories.GameRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;


    @Test
    public void testCreateAGame() {
    	
        Game game = new Game();
        game.setName("Test Game");

        when( gameRepository.save( any() ) ).thenReturn(game);

        Game result = gameService.createAGame(game);

        assertEquals( game.getName(), result.getName() );
    }


    @Test
    public void testFindById() {
    	
        UUID gameId = UUID.randomUUID();
        
        Game game = new Game();
        
        when( gameRepository.findById(gameId) ).thenReturn( Optional.of(game) );

        Optional<Game> result = gameService.findById( gameId );

        assertTrue( result.isPresent() );
    }


    @Test
    public void testFindAll() {
    	
        List<Game> games = Arrays.asList( new Game(), new Game() );
        
        when( gameRepository.findAll() ).thenReturn( games );

        List<Game> result = gameService.findAll();

        assertEquals( 2, result.size() );
    }


    @Test
    public void testFindByPublisherId() {

        String publisherId = "test-publisher";
        
        List<Game> games = Arrays.asList( new Game(), new Game() );
        
        when( gameRepository.findByPublisherId(publisherId) ).thenReturn(games);

        List<Game> result = gameService.findByPublisherId(publisherId);

        assertEquals( 2, result.size() );
    }


}