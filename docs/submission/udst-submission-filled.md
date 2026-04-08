Design Patterns and Modeling | UDST Submission Template

1-Week Course Project | Up to 3 Students

Application name / title:
Resume.AI - GenAI Resume Analyzer

Technology / language used:
Java / Spring Boot

Brief description of what the application does (2-3 sentences):
Resume.AI accepts a candidate resume and target job description, then generates an AI-based evaluation and improvement guidance. The system supports multiple analysis modes, including quick scoring, detailed critique, and cover letter generation. The architecture is organized using design patterns to keep model integration, prompt creation, and event logging modular.

AI-powered feature description (what it does and how the Azure endpoint is used):
The AI feature builds a structured prompt from user resume data and sends it to an LLM endpoint through a facade service. The model returns text (expected as JSON-shaped output) containing score, feedback, suggestions, and optionally a cover letter. The application processes the returned text and triggers observers for logging and output formatting after each analysis.

Pattern Entry 1

Pattern 1 - Pattern name: Strategy
Category: Behavioural
Intent: Selects one of multiple prompt-generation algorithms at runtime based on the requested analysis mode.
Problem it solves: Without Strategy, prompt logic for all modes would be mixed in one class, making feature growth and testing harder.
How it is applied: We defined AnalysisStrategy and implemented QuickAnalysisStrategy, DetailedAnalysisStrategy, and CoverLetterStrategy, each producing a different prompt from AnalysisRequest.
Interaction with other patterns: AIServiceFacade (Facade) accepts a selected strategy and executes it before calling the Gemini client.
Trade-offs: (+) Clean separation of prompt behavior and easier extension. (-) More classes and strategy selection wiring.
Class diagram: See docs/uml/all-pattern-class-diagrams.puml (StrategyPattern section)
Sequence diagram: N/A - no meaningful sequence interaction

Pattern Entry 2

Pattern 2 - Pattern name: Facade
Category: Structural
Intent: Provides one simplified service API for analysis while hiding low-level model client and event plumbing.
Problem it solves: Without Facade, controllers/services would directly manage model client calls, prompt execution, and event publication.
How it is applied: AIServiceFacade exposes analyze(prompt) and analyze(request, strategy), internally handling Gemini access and observer event publication.
Interaction with other patterns: Uses Singleton (GeminiClient) for client access and Strategy for prompt construction.
Trade-offs: (+) Reduced coupling and simpler call sites. (-) Risk of becoming a god class if too many responsibilities are added.
Class diagram: See docs/uml/all-pattern-class-diagrams.puml (FacadePattern section)
Sequence diagram: N/A - no meaningful sequence interaction

Pattern Entry 3

Pattern 3 - Pattern name: Singleton
Category: Creational
Intent: Guarantees one shared initialized model client instance for the entire application lifecycle.
Problem it solves: Prevents repeated model client initialization, inconsistent configuration, and resource waste.
How it is applied: GeminiClient stores a private static instance, private constructor, and synchronized getInstance().
Interaction with other patterns: Facade uses Singleton to obtain the model Client for each analysis call.
Trade-offs: (+) Controlled shared resource and consistent API key setup. (-) Harder to mock in tests and can hide global state dependencies.
Class diagram: See docs/uml/all-pattern-class-diagrams.puml (SingletonPattern section)
Sequence diagram: N/A - no meaningful sequence interaction

Pattern Entry 4

Pattern 4 - Pattern name: Observer
Category: Behavioural
Intent: Publishes analysis completion events to decoupled listeners.
Problem it solves: Avoids hardcoding post-processing logic (logging/formatting) inside core analysis flow.
How it is applied: AnalysisEventPublisher maintains AnalysisObserver subscribers. ConsoleLogObserver and ResultFormatterObserver subscribe and react to notifyAnalysisCompleted().
Interaction with other patterns: Facade triggers observer notifications after strategy-driven model analysis returns.
Trade-offs: (+) Extensible event reactions with low coupling. (-) Event flow can be harder to trace when observers increase.
Class diagram: See docs/uml/all-pattern-class-diagrams.puml (ObserverPattern section)
Sequence diagram: N/A - no meaningful sequence interaction

Pattern Entry 5

Pattern 5 - Pattern name: Prompt Template + Structured Output
Category: GenAI Pattern
Intent: Standardizes prompts and response format so model outputs are predictable and easier to process.
Problem it solves: Without prompt templates and output constraints, responses become inconsistent and harder to parse or compare across modes.
How it is applied: Each strategy builds a template prompt with role instructions and fixed output keys (score, feedback, suggestions, coverLetter), guiding the model toward schema-like responses.
Interaction with other patterns: Strategy selects the right prompt template, then Facade sends it to the model and Observer handles post-response logging/format preview.
Trade-offs: (+) Better response consistency and simpler downstream handling. (-) More prompt maintenance and occasional rigidity in model creativity.
Class diagram: See docs/uml/all-pattern-class-diagrams.puml (StrategyPattern and FacadePattern sections)
Sequence diagram: N/A - no meaningful sequence interaction

Application Screenshots

Screenshot guidance
Include at least 4 screenshots: one showing the AI feature responding to a real input, and one per major pattern interaction.

Screenshot 1 - AI Feature in Action
[ Paste screenshot here ]
Caption:
A resume and job description were submitted in detailed mode. The application called the model endpoint and returned a JSON-like response containing score, feedback, suggestions, and optional cover letter.

Screenshot 2 - Pattern Interaction A
[ Paste screenshot here ]
Caption:
Strategy pattern in action: selected mode routed to DetailedAnalysisStrategy, which generated a detailed analysis prompt before facade execution.

Screenshot 3 - Pattern Interaction B
[ Paste screenshot here ]
Caption:
Observer pattern in action: after model response, AnalysisEventPublisher notified ConsoleLogObserver and ResultFormatterObserver.

Screenshot 4 - Additional Evidence (optional)
[ Paste screenshot here ]
Caption:
Singleton usage evidence: multiple analysis calls reused one GeminiClient instance initialized from environment key.

Reflection

R1 Pattern justification
We used Strategy instead of if-else prompt logic because each analysis mode needed independent prompt evolution and testing.
We used Facade instead of direct model calls across the codebase because we wanted a single stable integration point.
We used Singleton instead of creating a client per request because model client setup should be shared and consistent.
We used Observer instead of inline logging/formatting because post-analysis actions should be independently extensible.
We used Prompt Template + Structured Output (GenAI) instead of free-form prompting because stable response structure is required for reliable application behavior.

R2 GenAI integration
The feature takes an AnalysisRequest, selects a strategy to build a prompt, and sends that prompt to the model endpoint through AIServiceFacade. The prompt is template-based and requests structured output with fixed fields: score, feedback, suggestions, and coverLetter. The model returns generated text, which the application returns to callers and then passes to observers for logging/format preview. Formally used GenAI patterns include Prompt Template Pattern (mode-specific prompt templates), Structured Output Pattern (fixed response keys), and Role Prompting (system role instruction in each strategy prompt).

R3 Pattern composition
A user request with mode=cover-letter triggers Strategy selection first, choosing CoverLetterStrategy to build a specialized prompt. AIServiceFacade then executes that prompt and receives the model result through the singleton client. Immediately after completion, Facade invokes AnalysisEventPublisher, which notifies ConsoleLogObserver and ResultFormatterObserver. The combined effect is clean separation of generation logic and post-processing without coupling either concern to request-handling code.

R4 Challenges and solutions
The biggest challenge was keeping responsibilities separated while integrating model calls quickly. Early versions risked mixing prompt generation, model invocation, and response-side logging in one service. We addressed this by formalizing Strategy for prompt construction and adding Observer for post-analysis reactions. This kept AIServiceFacade focused on orchestration and made each concern easier to test and extend.

R5 AI tool usage
Yes, we used AI coding assistants for boilerplate generation, UML draft creation, and refactoring suggestions. Every generated snippet was reviewed and adapted to our package structure, naming conventions, and runtime flow. We verified correctness through compilation, manual endpoint tests, and code walkthroughs for pattern conformance. We also adjusted generated text prompts to align with assignment requirements.

R6 What would you do differently?
With two more weeks, we would fully implement Builder in production flow and add a dedicated strategy factory for cleaner mode-to-strategy resolution. We would also add structured JSON parsing with schema validation instead of plain text pass-through from the model. For architecture, we would externalize observer registration using Spring configuration for better testability and environment-specific behavior.

R7 Key learning
The most important learning was that pattern composition matters more than pattern count. In practice, small interactions (Strategy feeding Facade, then Facade triggering Observer) gave the biggest maintainability gains. This was less obvious from theory, where patterns are often taught in isolation. Real value came from clear boundaries between changing concerns.
