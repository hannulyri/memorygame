package fi.hmp.memorygame.repository;

import java.util.List;

import fi.hmp.memorygame.domain.Card;
import fi.hmp.memorygame.domain.enums.CardType;


/**
 * @author Hannu Mäki-Panula
 */
public interface ExtraGameRepository {
	
	List<Card> findByCardType(CardType cardType);

}
