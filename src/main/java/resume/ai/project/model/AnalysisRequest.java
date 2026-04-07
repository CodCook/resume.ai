package resume.ai.project.model;

public class AnalysisRequest {
    private final String userName;
    private final String resumeText;
    private final String jobDescription;
    private final String mode;

    public AnalysisRequest(String userName, String resumeText, String jobDescription, String mode) {
        this.userName = userName;
        this.resumeText = resumeText;
        this.jobDescription = jobDescription;
        this.mode = mode;
    }

    public String getUserName() {
        return userName;
    }

    public String getResumeText() {
        return resumeText;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getMode() {
        return mode;
    }
}