package resume.ai.project.builder;

import resume.ai.project.model.AnalysisRequest;

public class AnalysisRequestBuilder {
    private String userName;
    private String resumeText;
    private String jobDescription;
    private String mode;

    public AnalysisRequestBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public AnalysisRequestBuilder withResume(String resumeText) {
        this.resumeText = resumeText;
        return this;
    }

    public AnalysisRequestBuilder withJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
        return this;
    }

    public AnalysisRequestBuilder withMode(String mode) {
        this.mode = mode;
        return this;
    }

    public AnalysisRequest build() {
        return new AnalysisRequest(userName, resumeText, jobDescription, mode);
    }
}