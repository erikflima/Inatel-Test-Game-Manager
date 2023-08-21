package com.gamemanager.app.repositories;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gamemanager.app.entities.Game;

public interface GameRepository extends JpaRepository<Game, UUID> {

	public List<Game> findByPublisherId( String publisherId );
}