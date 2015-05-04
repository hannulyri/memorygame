package fi.hmp.memorygame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.hmp.memorygame.domain.Highscore;
import fi.hmp.memorygame.domain.User;

/**
 * @author Hannu Mäki-Panula
 */
public interface HighscoreRepository extends JpaRepository<Highscore, Long>, ExtraHighscoreRepository {
	Highscore findByName(String string);

	Highscore findByUser(User user);
	
}
