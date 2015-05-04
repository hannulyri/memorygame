package fi.hmp.memorygame.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fi.hmp.memorygame.Application;
import fi.hmp.memorygame.domain.Authority;
import fi.hmp.memorygame.domain.User;
import fi.hmp.memorygame.security.AuthoritiesConstants;
import fi.hmp.memorygame.service.GameService;
import fi.hmp.memorygame.service.UserService;
import fi.hmp.memorygame.web.rest.dto.UserDTO;

/**
 * Test class for the AccountResource REST controller.
 *
 * @see UserService
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class GameResourceTest {

    @Inject
    private GameService gameService;

    //private MockMvc restUserMockMvc;

    private MockMvc restMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        GameResource gameResource = new GameResource();
        ReflectionTestUtils.setField(gameResource, "gameService", gameService);

        this.restMvc = MockMvcBuilders.standaloneSetup(gameResource).build();
    }

    @Test
    public void testInitGame() throws Exception {
    	restMvc.perform(post("/api/game")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.cardBack").exists() )
                .andExpect(jsonPath("$.cardBack.cardImage").exists() )
                               
                .andExpect(jsonPath("$.cards[0,1]").exists() )                
                ;
    	
    }


}
