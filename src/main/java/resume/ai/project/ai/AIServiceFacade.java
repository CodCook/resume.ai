package resume.ai.project.ai;

import org.springframework.stereotype.Service;

import com.google.genai.Client;

@Service
public class AIServiceFacade {

    public String analyze(String prompt) {
        try {
            Client client = GeminiClient.getInstance().getClient();
            return client.models.generateContent("gemini-2.0-flash", prompt, null).text();
        } catch (Exception e) {
            return "Error analyzing prompt: " + e.getMessage();
        }
    }
}
