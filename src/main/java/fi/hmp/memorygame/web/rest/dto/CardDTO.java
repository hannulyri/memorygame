package fi.hmp.memorygame.web.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Hannu Mäki-Panula
 */
public class CardDTO {
   
    private Long id;

    private String cardImage;
    
	private boolean flipped;
    
    private boolean guessed;
    
    private Integer order;
           
	public CardDTO() {

    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }
    
    public boolean isFlipped() {
		return flipped;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}

	public boolean isGuessed() {
		return guessed;
	}

	public void setGuessed(boolean guessed) {
		this.guessed = guessed;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
    
    
   
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cardImage", cardImage)
                .toString();
    }
}
