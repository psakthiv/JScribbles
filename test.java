import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DBSophiaConfigTest {

    @Mock
    private Environment env;

    @InjectMocks
    private DBSophiaConfig config;

    @Test
    public void testDataSourceBean() {
        // Mock the environment variables if needed
        when(env.getProperty("spring.datasource.url")).thenReturn("jdbc:testUrl");
        when(env.getProperty("spring.datasource.username")).thenReturn("testUser");
        when(env.getProperty("spring.datasource.password")).thenReturn("testPass");

        // Call the method under test
        HikariDataSource dataSource = config.dataSource();

        // Verify results
        assertNotNull(dataSource);
        assertEquals("jdbc:testUrl", dataSource.getJdbcUrl());
        assertEquals("testUser", dataSource.getUsername());
        assertEquals("testPass", dataSource.getPassword());
    }
}
