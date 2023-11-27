import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(/* specify your Spring configuration class here */)
@WebAppConfiguration
public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }

    @Test
    public void accessPublicEndpointWithoutAuth_shouldBeOk() throws Exception {
        mvc.perform(get("/test/api"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void accessPrivateEndpointWithAuth_shouldBeOk() throws Exception {
        mvc.perform(get("/v1/template/"))
                .andExpect(status().isOk());
    }

    @Test
    public void accessPrivateEndpointWithoutAuth_shouldBeUnauthorized() throws Exception {
        mvc.perform(get("/v1/template/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void accessEndpointWithCsrfDisabled_shouldBeOk() throws Exception {
        mvc.perform(get("/v1/template/").with(csrf().disable()))
                .andExpect(status().isOk());
    }

    // Add more tests as needed for different roles, other endpoints, etc.
}

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CustomAuthenticationProviderTest {

    @InjectMocks
    private AuthenticationProvider authenticationProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // initialize your AuthenticationProvider here if needed
    }

    @Test
    public void authenticate_withCorrectCredentials_shouldSucceed() throws AuthenticationException {
        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password");
        when(authentication.getName()).thenReturn("user");
        when(authentication.getCredentials()).thenReturn("password");

        Authentication result = authenticationProvider.authenticate(authentication);

        assertNotNull(result);
        assertTrue(result.isAuthenticated());
        assertEquals("user", result.getName());
        assertTrue(AuthorityUtils.authorityListToSet(result.getAuthorities()).contains("USER"));
    }

    @Test(expected = AuthenticationException.class)
    public void authenticate_withIncorrectCredentials_shouldFail() throws AuthenticationException {
        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "wrongpassword");
        when(authentication.getName()).thenReturn("user");
        when(authentication.getCredentials()).thenReturn("wrongpassword");

        authenticationProvider.authenticate(authentication);
    }

    @Test
    public void supports_withCorrectAuthenticationToken_shouldReturnTrue() {
        boolean supports = authenticationProvider.supports(UsernamePasswordAuthenticationToken.class);

        assertTrue(supports);
    }
}

