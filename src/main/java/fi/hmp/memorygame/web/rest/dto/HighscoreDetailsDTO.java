package fi.hmp.memorygame.web.rest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Hannu Mäki-Panula
 */
public class HighscoreDetailsDTO {

    private String name;
    
    private String guesses;

    private String time;

	public HighscoreDetailsDTO() {

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
	
    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}	

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("guesses", guesses)
                .toString();
    }
}
