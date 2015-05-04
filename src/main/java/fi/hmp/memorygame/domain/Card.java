package fi.hmp.memorygame.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import fi.hmp.memorygame.domain.enums.CardType;

/**
 * @author Hannu Mäki-Panula
 */
@Entity
@Table(name = "cards")
public class Card extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "card_image", length = 100, nullable = false, unique = true)
    private String cardImage;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", length = 20, nullable = false)
    private CardType cardType;    

	public Card() {

    }

    public static Builder getBuilder() {
        return new Builder();
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getCardImage() {
        return cardImage;
    }
    
    public CardType getCardType() {
        return cardType;
    }    
        
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("cardImage", cardImage)
                .append("cardType", cardType)
                .append("version", this.getVersion())
                .toString();
    }

    public static class Builder {

        private Card card;

        public Builder() {
        	card = new Card();
        }
        
        public Builder name(String cardImage) {
        	card.cardImage = cardImage;
            return this;
        }
        
        public Builder cardType(CardType cardType) {
        	card.cardType = cardType;
            return this;
        }        
        
        public Card build() {
            return card;
        }


    }

	public Card build() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateName(String cardImage) {
		this.cardImage = cardImage;
	}
	

}
