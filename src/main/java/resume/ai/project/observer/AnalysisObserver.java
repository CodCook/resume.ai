package resume.ai.project.observer;

import resume.ai.project.model.AnalysisRequest;

public interface AnalysisObserver {
    void onAnalysisCompleted(AnalysisRequest request, String result);
}