@ExtendWith(MockitoExtension.class)
public class DealworksDocumentImageServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private DealworksConfiguration dealworksConfiguration;

    @InjectMocks
    private DealworksDocumentImageServiceImpl service;

    // Example test case
    @Test
    void testRetrieveDocumentImage_Success() {
        // setup
        String documentId = "testDocumentId";
        String imageId = "testImageId";
        String expectedUrl = "http://example.com";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAuthorization("Bearer accessToken");
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(new LinkedMultiValueMap<>(), headers);
        
        // mock behavior
        Mockito.when(dealworksConfiguration.getDocument().getDownloadURL()).thenReturn(expectedUrl);
        Mockito.when(restTemplate.exchange(
            anyString(),
            any(HttpMethod.class),
            any(HttpEntity.class),
            eq(Resource.class))
        ).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        // invoke
        ResponseEntity response = service.retrieveDocumentImage(documentId, imageId);

        // verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(restTemplate).exchange(
            eq(expectedUrl),
            eq(HttpMethod.POST),
            eq(requestEntity),
            eq(Resource.class)
        );
    }
}
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBTemplateConfigTest {

    private DBTemplateConfig dbTemplateConfig;
    private DataSource dataSourceMock;

    @Before
    public void setUp() {
        dataSourceMock = mock(DataSource.class);
        dbTemplateConfig = new DBTemplateConfig(dataSourceMock);
    }

    @Test
    public void testJdbcTemplateBean() {
        JdbcTemplate jdbcTemplate = dbTemplateConfig.jdbcTemplate();
        assertNotNull(jdbcTemplate);
        // Additional checks can be added here
    }

    @Test
    public void testNamedParameterJdbcTemplateBean() {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = dbTemplateConfig.namedParameterJdbcTemplate();
        assertNotNull(namedParameterJdbcTemplate);
        // Additional checks can be added here
    }
}
