package resume.ai.project.strategy;

import resume.ai.project.model.AnalysisRequest;

public class CoverLetterStrategy implements AnalysisStrategy {
    @Override
    public String buildPrompt(AnalysisRequest request) {
        return String.format(
                """
                You are a professional career writer.
                Return JSON with keys: score, feedback, suggestions, coverLetter.
                Generate a tailored one-page cover letter in coverLetter based on the resume and target role.
                Keep feedback and suggestions short, and focus mainly on the cover letter quality.

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