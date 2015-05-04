package fi.hmp.memorygame.service;

import java.util.List;

import fi.hmp.memorygame.web.rest.dto.CardDTO;
import fi.hmp.memorygame.web.rest.dto.GameDTO;

/**
 * @author Hannu Mäki-Panula
 */
public interface GameService {
	public List<CardDTO> findAll();

	public GameDTO initGame();
}
