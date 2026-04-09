package resume.ai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import resume.ai.project.ai.AIServiceFacade;
import resume.ai.project.builder.AnalysisRequestBuilder;
import resume.ai.project.model.AnalysisRequest;
import resume.ai.project.model.AnalysisResult;
import resume.ai.project.strategy.AnalysisStrategy;
import resume.ai.project.strategy.CoverLetterStrategy;
import resume.ai.project.strategy.DetailedAnalysisStrategy;
import resume.ai.project.strategy.QuickAnalysisStrategy;

@RestController
@RequestMapping("/")
public class ResumeController {

    private final AIServiceFacade aiServiceFacade;

    @Autowired
    public ResumeController(AIServiceFacade aiServiceFacade) {
        this.aiServiceFacade = aiServiceFacade;
    }

    @PostMapping("/analyze")
    public AnalysisResult analyzeResume(@RequestBody AnalysisRequest rawRequest) {

        AnalysisRequest request = new AnalysisRequestBuilder()
                .withUserName(rawRequest.getUserName())
                .withResume(rawRequest.getResumeText())
                .withJobDescription(rawRequest.getJobDescription())
                .withMode(rawRequest.getMode())
                .build();

        AnalysisStrategy strategy = chooseStrategy(request.getMode());

        String resultText = aiServiceFacade.analyze(request, strategy);

        AnalysisResult result = new AnalysisResult();
        result.setScore(80);
        result.setFeedback("Analysis completed successfully. Raw AI response: " + resultText);
        result.setSuggestions("Add more measurable achievements and role-specific keywords.");
        result.setCoverLetter(null);

        if ("cover-letter".equalsIgnoreCase(request.getMode())) {
            result.setCoverLetter(resultText);
            result.setFeedback("Cover letter generated successfully.");
            result.setSuggestions("Review and personalize the greeting and closing before sending.");
        }

        return result;
    }

    private AnalysisStrategy chooseStrategy(String mode) {
        if (mode == null) {
            return new DetailedAnalysisStrategy();
        }

        if (mode.equalsIgnoreCase("quick")) {
            return new QuickAnalysisStrategy();
        } else if (mode.equalsIgnoreCase("cover-letter")) {
            return new CoverLetterStrategy();
        } else {
            return new DetailedAnalysisStrategy();
        }
    }
}