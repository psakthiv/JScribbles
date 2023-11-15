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
