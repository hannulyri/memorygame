package fi.hmp.memorygame.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fi.hmp.memorygame.Application;
import fi.hmp.memorygame.config.Constants;
import fi.hmp.memorygame.domain.Authority;
import fi.hmp.memorygame.domain.Highscore;
import fi.hmp.memorygame.domain.User;
import fi.hmp.memorygame.repository.HighscoreRepository;
import fi.hmp.memorygame.repository.UserRepository;
import fi.hmp.memorygame.security.AuthoritiesConstants;
import fi.hmp.memorygame.service.HighscoreService;
import fi.hmp.memorygame.service.UserService;
import fi.hmp.memorygame.web.rest.dto.HighscoreDTO;

/**
 * Test class for the HighscoreResource REST controller.
 *
 * @see UserService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class HighscoreResourceTest {

    @Inject
    private UserRepository userRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private HighscoreRepository highscoreRepository;    
    
    @Inject
    private HighscoreService highscoreService;    
    
    @Mock
    private UserService mockUserService;
    
    private MockMvc restUserMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        HighscoreResource highscoreMockResource = new HighscoreResource();
        ReflectionTestUtils.setField(highscoreMockResource, "userService", mockUserService);
        ReflectionTestUtils.setField(highscoreMockResource, "highscoreService", highscoreService);

        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(highscoreMockResource).build();
    }

    @Test
    public void testHighscoreLoad() throws Exception {
        restUserMockMvc.perform(get("/api/highscore")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                ;
    }
    
    @Test
    @Transactional
    public void testAddAnonymousHighscore() throws Exception {
    	when(mockUserService.getUserWithAuthorities()).thenReturn(null);

        // Good
    	HighscoreDTO h = new HighscoreDTO();
    	h.setName("testHighscore");
    	h.setGuesses(String.valueOf(String.valueOf(Constants.SYSTEM_HIGHSCORE_MIN)));

        // Good user
    	restUserMockMvc.perform(
            post("/api/highscore")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(h)))
            .andExpect(status().isCreated());

        Highscore highscore = highscoreRepository.findByName("testHighscore");
        assertThat(highscore).isNotNull();
    }   
    
    @Test
    @Transactional
    public void testAddAnonymousHighscoreTooLowGuesses() throws Exception {
    	when(mockUserService.getUserWithAuthorities()).thenReturn(null);

        // Good
    	HighscoreDTO h = new HighscoreDTO();
    	h.setName("testHighscore2");
    	h.setGuesses(String.valueOf(String.valueOf(Constants.SYSTEM_HIGHSCORE_MIN-1)));

        // Good user
    	restUserMockMvc.perform(
            post("/api/highscore")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(h)))
            .andExpect(status().isBadRequest());

        Highscore highscore = highscoreRepository.findByName("testHighscore2");
        assertThat(highscore).isNull();
    }
    
    @Test
    @Transactional
    public void testAddAnonymousHighscoreTooHighGuesses() throws Exception {
    	when(mockUserService.getUserWithAuthorities()).thenReturn(null);

        // Good
    	HighscoreDTO h = new HighscoreDTO();
    	h.setName("testHighscore3");
    	h.setGuesses(String.valueOf(String.valueOf(Constants.SYSTEM_HIGHSCORE_MAX+1)));

        // Good user
    	restUserMockMvc.perform(
            post("/api/highscore")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(h)))
            .andExpect(status().isBadRequest());

        Highscore highscore = highscoreRepository.findByName("testHighscore3");
        assertThat(highscore).isNull();
    } 
    
    @Test
    @Transactional
    public void testAddAnonymousHighscoreNoNameNotLoggedIn() throws Exception {
    	when(mockUserService.getUserWithAuthorities()).thenReturn(null);

        // Good
    	HighscoreDTO h = new HighscoreDTO();
    	h.setName("");
    	h.setGuesses(String.valueOf(String.valueOf(Constants.SYSTEM_HIGHSCORE_MIN)));

        // Good user
    	restUserMockMvc.perform(
            post("/api/highscore")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(h)))
            .andExpect(status().isBadRequest());
    }   

    @Test
    @Transactional
    public void testAddLoggedInHighscoreNoName() throws Exception {
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setName(AuthoritiesConstants.ADMIN);
        authorities.add(authority);
        
        User user = userRepository.findOneByLogin("user");
        user.setCreatedBy(Constants.SYSTEM_ANONYMOUS);

        when(mockUserService.getUserWithAuthorities()).thenReturn(user);

        // Good
    	HighscoreDTO h = new HighscoreDTO();
    	h.setName("");
    	h.setGuesses(String.valueOf(String.valueOf(Constants.SYSTEM_HIGHSCORE_MIN)));

        // Good user
    	restUserMockMvc.perform(
            post("/api/highscore")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(h)))
            .andExpect(status().isCreated());

        Highscore highscore = highscoreRepository.findByUser(user);
        assertThat(highscore).isNotNull();
    }      
}
