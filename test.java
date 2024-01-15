import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import com.mongodb.client.Mongo
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDBServiceTest {
@Mock
private MongoTemplate mongoTemplate;

@InjectMocks
private MongoDBService mongoDBService;

@Before
public void setUp() {
    MockitoAnnotations.initMocks(this);
}

@Test
public void testFetchDocumentDMSWithValidId() {
    // Arrange
    String documentId = "validDocumentId";
    String imageId = "validImageId";
    Document mockDocument = new Document("document", new Document("metadata", 
                                new Document("core", new Document("dms", new Document("name", "testName")))))
                                .append("images", asList(new Document("imageId", imageId).append("format", "jpeg")));
								
    when(mongoTemplate.getCollection(anyString())).thenReturn(mock(MongoCollection.class));
    MongoCollection<Document> collection = mongoTemplate.getCollection("iseearch_document");
    when(collection.find(any())).thenReturn(mock(FindIterable.class));
    FindIterable<Document> findIterable = collection.find();
    when(findIterable.first()).thenReturn(mockDocument);

    // Act
    Map<String, String> result = mongoDBService.fetchDocumentDMS(documentId, imageId);

    // Assert
    assertNotNull("Result should not be null", result);
    assertEquals("Test name should match", "testName", result.get(Constants.SOURCEID_CODE));
    assertEquals("
	
	    when(mongoTemplate.getCollection(anyString())).thenReturn(mock(MongoCollection.class));
    MongoCollection<Document> collection = mongoTemplate.getCollection("iseearch_document");
    when(collection.find(any())).thenReturn(mock(FindIterable.class));
    FindIterable<Document> findIterable = collection.find();
    when(findIterable.first()).thenReturn(mockDocument);

    // Act
    Map<String, String> result = mongoDBService.fetchDocumentDMS(documentId, imageId);

    // Assert
    assertNotNull("Result should not be null", result);
    assertEquals("Test name should match", "testName", result.get(Constants.SOURCEID_CODE));
    assertEquals("


