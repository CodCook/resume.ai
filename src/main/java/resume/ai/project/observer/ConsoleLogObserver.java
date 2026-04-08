package resume.ai.project.observer;

import resume.ai.project.model.AnalysisRequest;

public class ConsoleLogObserver implements AnalysisObserver {
    @Override
    public void onAnalysisCompleted(AnalysisRequest request, String result) {
        String mode = request == null ? "unknown" : request.getMode();
        String userName = request == null ? "unknown" : request.getUserName();
        System.out.println("[Observer][ConsoleLog] Analysis completed for user='" + userName + "' mode='" + mode + "'.");
    }
}