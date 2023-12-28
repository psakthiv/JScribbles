import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

class MimeUtilTest {

    @Test
    void getMimeTypeFromFileNameTest() {
        try (MockedStatic<MimeUtil> mockedStatic = Mockito.mockStatic(MimeUtil.class)) {
            // Define the behavior for the static method
            mockedStatic.when(() -> MimeUtil.getMimeTypes(anyString()))
                        .thenReturn(Collections.singletonList(new MimeType("application/pdf")));
            // Call the method under test
            String mimeType = MimeUtil.getMimeTypeFromFileName("test.pdf");
            // Assert the result
            assertEquals("application/pdf", mimeType);
        }
    }
}
