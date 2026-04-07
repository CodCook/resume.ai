package resume.ai.project.strategy;

import resume.ai.project.model.AnalysisRequest;

public interface AnalysisStrategy {
    String buildPrompt(AnalysisRequest request);
}