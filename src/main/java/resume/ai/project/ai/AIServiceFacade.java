package resume.ai.project.ai;

import org.springframework.stereotype.Service;

import com.google.genai.Client;

import resume.ai.project.model.AnalysisRequest;
import resume.ai.project.observer.AnalysisEventPublisher;
import resume.ai.project.observer.ConsoleLogObserver;
import resume.ai.project.observer.ResultFormatterObserver;
import resume.ai.project.strategy.AnalysisStrategy;

@Service
public class AIServiceFacade {
    private final AnalysisEventPublisher eventPublisher;

    public AIServiceFacade() {
        this.eventPublisher = new AnalysisEventPublisher();
        this.eventPublisher.subscribe(new ConsoleLogObserver());
        this.eventPublisher.subscribe(new ResultFormatterObserver());
    }

    public String analyze(String prompt) {
        try {
            Client client = GeminiClient.getInstance().getClient();
            return client.models.generateContent("gemini-2.0-flash", prompt, null).text();
        } catch (Exception e) {
            return "Error analyzing prompt: " + e.getMessage();
        }
    }

    public String analyze(AnalysisRequest request, AnalysisStrategy strategy) {
        String prompt = strategy.buildPrompt(request);
        String result = analyze(prompt);
        eventPublisher.notifyAnalysisCompleted(request, result);
        return result;
    }

    public AnalysisEventPublisher getEventPublisher() {
        return eventPublisher;
    }
}
