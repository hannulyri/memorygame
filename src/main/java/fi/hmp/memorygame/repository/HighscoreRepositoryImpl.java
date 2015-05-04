package fi.hmp.memorygame.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fi.hmp.memorygame.domain.Highscore;

/**
 * @author Hannu Mäki-Panula
 */
public class HighscoreRepositoryImpl implements ExtraHighscoreRepository {
	
    @PersistenceContext
    private EntityManager em;

	@Override
	public List<Highscore> getTopTen() {
		TypedQuery<Highscore> query = em.createQuery("select h from Highscore h order by guesses asc, created_date asc", Highscore.class);
		query.setMaxResults(10);
		return query.getResultList();
	}	

}
