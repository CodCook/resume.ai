package resume.ai.project.ai;

import com.google.genai.Client;

public class GeminiClient {
    private static GeminiClient instance;
    private final Client client;

    private GeminiClient() {
        String apiKey = System.getenv("GEMINI_API_KEY");
        if (apiKey == null || apiKey.trim().isEmpty()) {
            apiKey = System.getProperty("GEMINI_API_KEY");
        }
        
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("GEMINI_API_KEY environment variable is null or blank");
        }
        
        // Note: adjust client instantiation if the SDK uses a different initialization approach
        this.client = Client.builder().apiKey(apiKey).build();
    }

    public static synchronized GeminiClient getInstance() {
        if (instance == null) {
            instance = new GeminiClient();
        }
        return instance;
    }

    public Client getClient() {
        return client;
    }
}
