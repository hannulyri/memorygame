package fi.hmp.memorygame.web.rest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import fi.hmp.memorygame.config.Constants;

/**
 * @author Hannu Mäki-Panula
 */
public class HighscoreDTO {

	private String name;
	
	@Min(Constants.SYSTEM_HIGHSCORE_MIN)
	@Max(Constants.SYSTEM_HIGHSCORE_MAX)
	@NotEmpty
    private String guesses;

	public HighscoreDTO() {

	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGuesses() {
		return guesses;
	}

	public void setGuesses(String guesses) {
		this.guesses = guesses;
	}	

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("guesses", guesses)
                .toString();
    }
}
