package resume.ai.project.model;

public class AnalysisResult {
    private int score;
    private String feedback;
    private String suggestions;
    private String coverLetter;

    public AnalysisResult() {
    }

    public AnalysisResult(int score, String feedback, String suggestions, String coverLetter) {
        this.score = score;
        this.feedback = feedback;
        this.suggestions = suggestions;
        this.coverLetter = coverLetter;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}