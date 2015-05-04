package fi.hmp.memorygame.repository;

import java.util.List;

import fi.hmp.memorygame.domain.Highscore;


/**
 * @author Hannu Mäki-Panula
 */
public interface ExtraHighscoreRepository {
	
	public List<Highscore> getTopTen();

}
