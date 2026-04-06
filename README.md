# AI Resume Analyzer

A GenAI-powered Spring Boot application that analyzes resumes against job descriptions and generates tailored feedback using the Google Gemini API.

Built for SOFT3202 — Design Patterns and Modeling | UDST

---

## Prerequisites

- Java 17+
- Maven 3.8+
- A Google Gemini API key ([get one here](https://aistudio.google.com/app/apikey))

---

## Setup

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd resume-analyzer
```

### 2. Install dependencies

```bash
mvn install
```

### 3. Set your API key as an environment variable

**Mac/Linux:**
```bash
export GEMINI_API_KEY=your_api_key_here
```

**Windows (Command Prompt):**
```cmd
set GEMINI_API_KEY=your_api_key_here
```

**Windows (PowerShell):**
```powershell
$env:GEMINI_API_KEY="your_api_key_here"
```

> ⚠️ Never paste your API key into the source code or commit it to GitHub.

---

## Running the Application

```bash
mvn spring-boot:run
```

The server will start at `http://localhost:8080`

---

## API Usage

### Analyze a Resume

**Endpoint:** `POST /analyze`

**Request Body (JSON):**
```json
{
  "userName": "Ahmed Al-Rashid",
  "resumeText": "Paste your full resume text here...",
  "jobDescription": "Paste the job description here...",
  "mode": "detailed"
}
```

**Mode options:**
| Mode | Description |
|------|-------------|
| `quick` | Short match score + top 3 gaps |
| `detailed` | Full analysis with scored sections and suggestions |
| `cover-letter` | Generates a tailored cover letter |

**Example using curl:**
```bash
curl -X POST http://localhost:8080/analyze \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "Ahmed",
    "resumeText": "Software engineer with 2 years experience in Java and Spring Boot...",
    "jobDescription": "We are looking for a backend engineer with Java, REST APIs, and cloud experience...",
    "mode": "detailed"
  }'
```

**Example Response:**
```json
{
  "score": 78,
  "feedback": "Your resume is a strong match for this role...",
  "suggestions": "Consider adding cloud certifications...",
  "coverLetter": null
}
```

---

## Design Patterns Used

| Pattern | Category | Location |
|---------|----------|----------|
| Builder | Creational | `builder/AnalysisRequestBuilder.java` |
| Facade | Structural | `ai/AIServiceFacade.java` |
| Strategy | Behavioural | `strategy/` |
| Observer | Behavioural | `observer/` |
| Singleton | Creational | `ai/GeminiClient.java` |

---

## Project Structure

```
src/main/java/com/resumeai/
├── controller/        → REST endpoints
├── model/             → Domain models
├── builder/           → Builder pattern
├── ai/                → Facade + Singleton (Gemini integration)
├── strategy/          → Analysis strategy implementations
└── observer/          → Event observers
```

---

## Team

| Name | Responsibility |
|------|---------------|
| [Student 1] | Gemini API integration, Facade, Singleton |
| [Student 2] | Builder, Models, REST Controller |
| [Student 3] | Strategy, Observer, UML Diagrams, Template |
