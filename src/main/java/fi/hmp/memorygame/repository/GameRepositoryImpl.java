package fi.hmp.memorygame.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fi.hmp.memorygame.domain.Card;
import fi.hmp.memorygame.domain.enums.CardType;

/**
 * @author Hannu Mäki-Panula
 */
public class GameRepositoryImpl implements ExtraGameRepository {
	
    @PersistenceContext
    private EntityManager em;	
    /*
    @Override
	public List<Card> findAllByBrand(Brand brand) {
		TypedQuery<Product> query = em.createQuery("select p from Product p where p.brand = :brand order by p.order asc", Product.class);
		query.setParameter("brand", brand);
		return query.getResultList();
	}
    */

	@Override
	public List<Card> findByCardType(CardType cardType) {
		TypedQuery<Card> query = em.createQuery("select c from Card c where c.cardType = :cardType", Card.class);
		//query.setParameter("cardType", cardType).setMaxResults(1);
		query.setParameter("cardType", cardType);
		return query.getResultList();
	}




	

}
