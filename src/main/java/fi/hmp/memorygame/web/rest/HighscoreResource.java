package fi.hmp.memorygame.web.rest;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.hmp.memorygame.config.Constants;
import fi.hmp.memorygame.domain.User;
import fi.hmp.memorygame.service.HighscoreService;
import fi.hmp.memorygame.service.UserService;
import fi.hmp.memorygame.web.rest.dto.HighscoreDTO;
import fi.hmp.memorygame.web.rest.dto.HighscoreDetailsDTO;

/**
 * @author Hannu Mäki-Panula
 */
@RestController
@RequestMapping("/api/highscore")
public class HighscoreResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HighscoreResource.class);
	
	@Inject
    private HighscoreService highscoreService;
	
	@Inject
    private UserService userService;

	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HighscoreDetailsDTO>> getHighscore() {
    	return new ResponseEntity<>(
    			highscoreService.getHighscoreList(),
    			HttpStatus.OK
                );    		
    }
	
	@RequestMapping(method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> addHighscore(
    		@Valid @RequestBody HighscoreDTO highscoreDTO,
    		BindingResult result,
    		HttpServletRequest httpReques    		    		
    		) {
    	JSONObject jsonResponse = new JSONObject();
    	JSONObject jsonErrors = new JSONObject();
    	
        if (result.hasErrors()) {
        	
            for (FieldError fieldError: result.getFieldErrors() ) {
            	jsonErrors.put(fieldError.getField(), fieldError.getCode() );
            }    
            jsonResponse.put("fieldErrors", jsonErrors);  
        	return new ResponseEntity<>(
        			jsonResponse,
        			HttpStatus.BAD_REQUEST
                    );               
        } 
        
        User user = userService.getUserWithAuthorities();
        

    	if (user != null && user.getCreatedBy().equals(Constants.SYSTEM_ACCOUNT.toString())) {
    		user = null;
    	}        

        if (user == null && highscoreDTO.getName().equals("")) {
        	jsonErrors.put("name", "NameIsRequired");        	  
        	jsonResponse.put("fieldErrors", jsonErrors);        	
        	return new ResponseEntity<>(
        			jsonResponse,
        			HttpStatus.BAD_REQUEST
                    );                   	
        }

                       
        highscoreService.addHighscore(user, highscoreDTO);
        jsonResponse.put("status", "ok");   
    	return new ResponseEntity<>(
    			jsonResponse,
    			HttpStatus.CREATED
                );                 
    }	

}
