package fi.hmp.memorygame.web.rest;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.hmp.memorygame.service.GameService;
import fi.hmp.memorygame.web.rest.dto.GameDTO;

/**
 * @author Hannu Mäki-Panula
 */
@RestController
@RequestMapping("/api/game")
public class GameResource {
	
	private final Logger log = LoggerFactory.getLogger(GameResource.class);
	
	@Inject
    private GameService gameService;
      
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDTO> initGame() {
    	
    	return new ResponseEntity<>(
        	gameService.initGame(),
        	HttpStatus.CREATED
            );    	
    }       

}