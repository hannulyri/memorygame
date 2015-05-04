package fi.hmp.memorygame.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import fi.hmp.memorygame.domain.User;
import fi.hmp.memorygame.web.rest.dto.HighscoreDTO;
import fi.hmp.memorygame.web.rest.dto.HighscoreDetailsDTO;

/**
 * @author Hannu Mäki-Panula
 */
public interface HighscoreService {

	public List<HighscoreDetailsDTO> getHighscoreList();

	public void addHighscore(User user, HighscoreDTO highscoreDTO);	

}
