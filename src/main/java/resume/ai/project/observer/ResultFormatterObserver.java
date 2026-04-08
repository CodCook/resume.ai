package resume.ai.project.observer;

import resume.ai.project.model.AnalysisRequest;

public class ResultFormatterObserver implements AnalysisObserver {
    private static final int PREVIEW_LENGTH = 120;

    @Override
    public void onAnalysisCompleted(AnalysisRequest request, String result) {
        String compact = result == null ? "" : result.replaceAll("\\s+", " ").trim();
        String preview = compact.length() <= PREVIEW_LENGTH ? compact : compact.substring(0, PREVIEW_LENGTH) + "...";
        String mode = request == null ? "unknown" : request.getMode();
        System.out.println("[Observer][ResultFormatter] mode='" + mode + "' preview='" + preview + "'");
    }
}