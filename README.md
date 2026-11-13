
@'
# SkillScan - Resume Analyzer

A web application that analyzes a resume and shows the results on a result page.

## Features
- Upload a resume and get an analysis
- View results on a clean result page
- Resume data stored using a repository layer

## Tech Stack
- Java
- Spring Boot
- Maven
- HTML templates (Thymeleaf)

## Project Structure
- `controller` - handles web requests
- `service` - resume analysis logic
- `model` - Resume entity
- `repository` - data access layer
- `templates` - index.html and result.html

## How to Run
1. Clone the repo:
   `git clone https://github.com/Anjalikhandelwal12/SkillScan-Resume-Analyzer.git`
2. Open it in your IDE (IntelliJ / VS Code)
3. Configure the database in `src/main/resources/application.properties`
4. Run: `./mvnw spring-boot:run`
5. Open `http://localhost:8080`

## Author
Anjali Khandelwal
[LinkedIn](https://linkedin.com/in/anjali-khandelwal-6ab477348) | [GitHub](https://github.com/Anjalikhandelwal12)
'@ | Set-Content README.md -Encoding UTF8