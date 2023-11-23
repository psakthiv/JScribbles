import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DocumentImageServiceTest {

    @Mock
    private DocumentImageServiceFactory documentImageServiceFactory;

    @Mock
    private ISearchAPIConnection iSearchAPI;

    @InjectMocks
    private DocumentImageService documentImageService;

    // Additional setup if needed...
}


@Test
public void testFetchDocumentSuccess() throws Exception {
    // Arrange
    String id = "someId";
    String imageId = "someImageId";
    String className = "someClassName";

    when(iSearchAPI.getSource(id, imageId)).thenReturn(className);
    
    DocumentImageConnectionService mockService = mock(DocumentImageConnectionService.class);
    when(documentImageServiceFactory.getService(className)).thenReturn(mockService);

    ResponseEntity<Resource> expectedResponse = ResponseEntity.ok().build();
    when(mockService.retrieveDocumentImage(id, imageId)).thenReturn(expectedResponse);

    // Act
    ResponseEntity<Resource> result = documentImageService.fetchDocument(id, imageId);

    // Assert
    assertEquals(expectedResponse, result);
}


@Test
public void testFetchDocumentImageRetrieveException() {
    // Arrange
    String id = "invalidId";
    String imageId = "invalidImageId";

    when(iSearchAPI.getSource(id, imageId)).thenReturn("");

    // Act & Assert
    assertThrows(ImageRetrieveException.class, () -> {
        documentImageService.fetchDocument(id, imageId);
    });
}
