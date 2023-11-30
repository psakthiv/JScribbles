import org.junit.jupiter.api.Test;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SecurityConfigurationTest {

    @Test
    public void testWebIgnoringConfigured() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Arrange
        SecurityConfiguration securityConfig = new SecurityConfiguration();
        WebSecurity webSecurity = mock(WebSecurity.class);
        WebSecurity.IgnoredRequestConfigurer ignoredRequestConfigurer = mock(WebSecurity.IgnoredRequestConfigurer.class);

        when(webSecurity.ignoring()).thenReturn(ignoredRequestConfigurer);

        // Act
        Method configureMethod = SecurityConfiguration.class
		.getDeclaredMethod("configure", WebSecurity.class);
        configureMethod.setAccessible(true);
        configureMethod.invoke(securityConfig, webSecurity);

        // Assert
        verify(webSecurity).ignoring();
        verify(ignoredRequestConfigurer).requestMatchers(any(RequestMatcher.class));
    }
}
