package fi.hmp.memorygame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.hmp.memorygame.domain.Card;

/**
 * @author Hannu Mäki-Panula
 */
public interface GameRepository extends JpaRepository<Card, Long>, ExtraGameRepository {

	

}
