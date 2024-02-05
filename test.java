public class MockSearchResponse {

    public static SearchResponse createMockSearchResponse() {
        SearchResponse mockResponse = new SearchResponse();

        // Assuming there is a constructor that allows setting these properties
        // Or you could use the setters if they exist
        mockResponse.setRequestId("mockRequestId");
        mockResponse.setOnBehalfOf("mockOnBehalfOf");
        mockResponse.setHasMoreMatches(true);

        List<Match> mockMatches = new ArrayList<>();
        // Populate the list with mock Match objects
        // Assuming Match has a constructor or setters to set properties
        Match mockMatch = new Match();
        mockMatch.setDocumentLocator("mockDocumentLocator");
        mockMatch.setMetaData(new ArrayList<MetadataItem>());
        // Add more properties to Match if needed
        mockMatches.add(mockMatch);

        mockResponse.setMatches(mockMatches);

        mockResponse.setErrorCode(0);
        mockResponse.setErrorName("No Error");
        mockResponse.setErrorMessage("Success");

        return mockResponse;
    }

    public static void main(String[] args) {
        SearchResponse mockResponse = createMockSearchResponse();
        // You can now
