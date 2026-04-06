package resume.ai.project;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import resume.ai.project.ai.AIServiceFacade;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		loadEnv();
		ApplicationContext context = SpringApplication.run(ProjectApplication.class, args);
		
		AIServiceFacade aiService = context.getBean(AIServiceFacade.class);
		String prompt = "Respond with only this exact JSON: {\"score\": 99, \"feedback\": \"Gemini is connected and working.\", \"suggestions\": \"none\", \"coverLetter\": null}";
		String result = aiService.analyze(prompt);
		System.out.println("Test Response:\n" + result);
	}

	private static void loadEnv() {
		try {
			Path envFile = Paths.get(".env");
			if (Files.exists(envFile)) {
				List<String> lines = Files.readAllLines(envFile);
				for (String line : lines) {
					line = line.trim();
					if (!line.isEmpty() && !line.startsWith("#")) {
						String[] parts = line.split("=", 2);
						if (parts.length == 2) {
							System.setProperty(parts[0].trim(), parts[1].trim());
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Failed to load .env file: " + e.getMessage());
		}
	}

}
