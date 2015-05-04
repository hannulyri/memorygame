package fi.hmp.memorygame.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import fi.hmp.memorygame.config.Constants;
import fi.hmp.memorygame.domain.Highscore;
import fi.hmp.memorygame.domain.User;
import fi.hmp.memorygame.repository.HighscoreRepository;
import fi.hmp.memorygame.repository.UserRepository;
import fi.hmp.memorygame.web.rest.dto.HighscoreDTO;
import fi.hmp.memorygame.web.rest.dto.HighscoreDetailsDTO;

/**
 * @author Hannu Mäki-Panula
 */
@Service
public class HighscoreServiceImpl implements HighscoreService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HighscoreServiceImpl.class);

    private HighscoreRepository highscoreRepository;
    private UserRepository userRepository;

    @Autowired
    public HighscoreServiceImpl(HighscoreRepository highscoreRepository, UserRepository userRepository) {
    	this.highscoreRepository = highscoreRepository;
    	this.userRepository = userRepository;
    }

    @Override
    public void addHighscore(User user, HighscoreDTO highscoreDTO) {
    	if (user != null && user.getCreatedBy().equals(Constants.SYSTEM_ACCOUNT.toString())) {
    		user = null;
    	}
		
		Highscore.Builder highscoreBuild = Highscore.getBuilder()
							
				.name(user == null ? highscoreDTO.getName() : user.getLogin())
				.user(user != null ? user : null)
				
				.guesses(Integer.valueOf(highscoreDTO.getGuesses()))
				;
		highscoreRepository.save(highscoreBuild.build());	
	}

	@Override
	public List<HighscoreDetailsDTO> getHighscoreList() {
		// TODO Auto-generated method stub
		return createListHighscoreDetailsDTO(highscoreRepository.getTopTen());
	}
	
	private List<HighscoreDetailsDTO> createListHighscoreDetailsDTO(List<Highscore> highscoreList) {
    	List<HighscoreDetailsDTO> list =  new ArrayList<>();
    	
    	for (Highscore obj : highscoreList) {
    		HighscoreDetailsDTO highscoreDetailsDTO = new HighscoreDetailsDTO();
    		
			String time = "";
			try {
				time = obj.getCreatedDate().toString("yyyy-MM-dd HH:mm");
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
			}    		

    		highscoreDetailsDTO.setName(obj.getName());
    		highscoreDetailsDTO.setGuesses(obj.getGuesses().toString());
    		highscoreDetailsDTO.setTime(time);

    		list.add(highscoreDetailsDTO);
    		highscoreDetailsDTO = null;    		
    	}
    	return list;
		
	}	
	
}
