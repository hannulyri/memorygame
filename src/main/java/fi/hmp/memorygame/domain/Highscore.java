package fi.hmp.memorygame.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Hannu Mäki-Panula
 */
@Entity
@Table(name = "highscore")
public class Highscore extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;       
    
    @Column(name = "guesses", nullable = false)
    private Integer guesses;     
    	
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Highscore() {

    }

    public static Builder getBuilder() {
        return new Builder();
    }

    @Override
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Integer getGuesses() {
        return guesses;
    }    
    
    public User getUser() {
        return user;
    }
   
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("version", this.getVersion())
                .toString();
    }

    public static class Builder {

        private Highscore highscore;

        public Builder() {
        	highscore = new Highscore();
        }
        
        public Builder name(String name) {
        	highscore.name = name;
            return this;
        }
        
        public Builder guesses(Integer guesses) {
        	highscore.guesses = guesses;
            return this;
        }        
        
        public Builder user(User user) {
        	highscore.user = user;
            return this;
        }

        public Highscore build() {
    		return highscore;
    	}
    }




}
