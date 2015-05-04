package fi.hmp.memorygame.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.hmp.memorygame.domain.Card;
import fi.hmp.memorygame.domain.enums.CardType;
import fi.hmp.memorygame.repository.GameRepository;
import fi.hmp.memorygame.web.rest.dto.CardDTO;
import fi.hmp.memorygame.web.rest.dto.GameDTO;

/**
 * @author Hannu Mäki-Panula
 */
@Service
public class GameServiceImpl implements GameService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

    private GameRepository cardRepository; 

    @Autowired
    public GameServiceImpl(GameRepository cardRepository) {
    	this.cardRepository = cardRepository;
    }

	@Override
	public List<CardDTO> findAll() {
		List<CardDTO> list =  new ArrayList<>();
		list.addAll(createListCardDTO(cardRepository.findAll()));
		list.addAll(createListCardDTO(cardRepository.findAll()));
		
		return list;
	}
	
	private List<CardDTO> createListCardDTO(List<Card> findAll) {
    	List<CardDTO> list =  new ArrayList<>();
    	
    	Integer counter = 0;
    	for (Card obj : findAll) {
    		CardDTO cardDTO = new CardDTO();

    		cardDTO.setId(obj.getId());
    		cardDTO.setCardImage(obj.getCardImage());
    		cardDTO.setFlipped(false);
    		cardDTO.setGuessed(false);
    		cardDTO.setOrder(counter);
    		
    		counter++;

    		list.add(cardDTO);
    		cardDTO = null;    		
    	}
    	return list;
		
	}
	
	private List<CardDTO> createDoubleListCardDTO(List<Card> cards) {
    	List<CardDTO> list =  new ArrayList<>();
    	List<Card> doubleList =  new ArrayList<>();
    	doubleList.addAll(cards);
    	doubleList.addAll(cards);

    	Collections.shuffle(doubleList, new Random());
    	
    	Integer counter = 0;
    	for (Card obj : doubleList) {
    		CardDTO cardDTO = new CardDTO();

    		cardDTO.setId(obj.getId());
    		cardDTO.setCardImage(obj.getCardImage());
    		//cardDTO.setFlipped(counter % 2 == 0 ? true : false);
    		cardDTO.setFlipped(false);
    		cardDTO.setGuessed(false);
    		cardDTO.setOrder(counter);
    		
    		counter++;

    		list.add(cardDTO);
    		cardDTO = null;    		
    	}     	
    	return list;
		
	}	
	

	public GameDTO initGame() {
		GameDTO game = new GameDTO();		
		game.setGuessCount(0);
		game.setFirstPick(null);
		game.setSecondPick(null);
		
		Random random = new Random();
		List<CardDTO> backCards =  createListCardDTO(cardRepository.findByCardType(CardType.BACK));
		game.setCardBack(backCards.get(random.nextInt(backCards.size())));
		
		List<CardDTO> frontCards =  createDoubleListCardDTO(cardRepository.findByCardType(CardType.FRONT));		
		
		 Map<Long,CardDTO> cardList = new HashMap<Long,CardDTO>();
		 
		 Long counter = Long.valueOf(0);
		 for (CardDTO c : frontCards) {
			 cardList.put(counter,c);
			 counter++;
		 }
		 
		 game.setCards(cardList);
		return game;
	}	
	
}
