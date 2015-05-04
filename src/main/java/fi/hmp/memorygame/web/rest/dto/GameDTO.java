package fi.hmp.memorygame.web.rest.dto;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Hannu Mäki-Panula
 */
public class GameDTO {
   
    private Long id;

	private CardDTO cardBack;
    
    private Long firstPick;
    
    private Long secondPick;
    
    private int guessCount;
    
    private Map<Long, CardDTO> cards;

           
	public GameDTO() {

    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public CardDTO getCardBack() {
		return cardBack;
	}

	public void setCardBack(CardDTO cardBack) {
		this.cardBack = cardBack;
	}

	public Long getFirstPick() {
		return firstPick;
	}

	public void setFirstPick(Long firstPick) {
		this.firstPick = firstPick;
	}

	public Long getSecondPick() {
		return secondPick;
	}

	public void setSecondPick(Long secondPick) {
		this.secondPick = secondPick;
	}

	public int getGuessCount() {
		return guessCount;
	}

	public void setGuessCount(int guessCount) {
		this.guessCount = guessCount;
	}

	public Map<Long, CardDTO> getCards() {
		return cards;
	}

	public void setCards(Map<Long, CardDTO> cardList) {
		this.cards = cardList;
	}
    

    
   
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .toString();
    }
}
