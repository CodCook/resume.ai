package resume.ai.project.strategy;

import resume.ai.project.model.AnalysisRequest;

public class DetailedAnalysisStrategy implements AnalysisStrategy {
    @Override
    public String buildPrompt(AnalysisRequest request) {
        return String.format(
                """
                You are an expert resume coach.
                Provide a detailed JSON response with keys: score, feedback, suggestions, coverLetter.
                In feedback, evaluate skills alignment, achievements, role fit, and ATS keyword coverage.
                In suggestions, provide concrete rewrite guidance and missing keywords to add.

                Candidate: %s
                Resume:
                %s

                Job Description:
                %s
                """,
                request.getUserName(),
                request.getResumeText(),
                request.getJobDescription());
    }
}