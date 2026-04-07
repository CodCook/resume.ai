package resume.ai.project.strategy;

import resume.ai.project.model.AnalysisRequest;

public class QuickAnalysisStrategy implements AnalysisStrategy {
    @Override
    public String buildPrompt(AnalysisRequest request) {
        return String.format(
                """
                You are an ATS resume analyzer.
                Provide a quick response in JSON with keys: score, feedback, suggestions, coverLetter.
                Keep feedback concise and list the top 3 gaps only.

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