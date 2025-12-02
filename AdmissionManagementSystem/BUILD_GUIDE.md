# Building & Running the Admission Management System

## Prerequisites

- Java 17 or later
- Maven 3.8.1 or later
- Git (optional)

## Quick Start

### 1. Navigate to Project Directory
```bash
cd /Users/sravan/Desktop/zip/TICKET\ BOOKING\ PROJECT/AdmissionManagementSystem
```

### 2. Build the Project
```bash
mvn clean install
```

This command will:
- Download all dependencies from Maven Central
- Compile the source code
- Run any tests
- Package the application as a JAR file

### 3. Run the Application
```bash
mvn spring-boot:run
```

Or build and run:
```bash
mvn clean install spring-boot:run
```

### 4. Access the Application
- **Main Application**: http://localhost:8080
- **H2 Database Console**: http://localhost:8080/h2-console
  - Driver: org.h2.Driver
  - URL: jdbc:h2:mem:testdb
  - User: sa
  - Password: (leave empty)

## API Endpoints Testing

### Using curl or Postman:

#### 1. Create a Course
```bash
POST http://localhost:8080/api/courses
Content-Type: application/json

{
  "courseName": "Java Advanced",
  "description": "Advanced Java Programming",
  "duration": 3,
  "level": "Advanced",
  "active": true
}
```

#### 2. Get All Courses
```bash
GET http://localhost:8080/api/courses/active
```

#### 3. Submit Application
```bash
POST http://localhost:8080/api/applications/submit
Content-Type: application/json

{
  "applicantName": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "1234567890",
  "address": "123 Main Street",
  "additionalInformation": "Experienced programmer",
  "courseId": 1
}
```

#### 4. Get All Applications (Admin)
```bash
GET http://localhost:8080/api/applications
```

#### 5. Update Application Status
```bash
PUT http://localhost:8080/api/applications/1/status
Content-Type: application/json

{
  "status": "SELECTED"
}
```

Or for rejection:
```bash
PUT http://localhost:8080/api/applications/1/status
Content-Type: application/json

{
  "status": "REJECTED",
  "rejectionReason": "Does not meet course prerequisites"
}
```

#### 6. Get Selected Students
```bash
GET http://localhost:8080/api/applications/status/selected
```

#### 7. Get Application Statistics
```bash
GET http://localhost:8080/api/applications/statistics
```

## Project Structure

```
AdmissionManagementSystem/
├── src/
│   ├── main/
│   │   ├── java/com/admission/
│   │   │   ├── AdmissionManagementApplication.java    # Main entry point
│   │   │   ├── config/
│   │   │   │   └── ApplicationConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── CourseController.java
│   │   │   │   └── ApplicationController.java
│   │   │   ├── service/
│   │   │   │   ├── CourseService.java
│   │   │   │   ├── ApplicationService.java
│   │   │   │   └── EmailService.java
│   │   │   ├── repository/
│   │   │   │   ├── CourseRepository.java
│   │   │   │   ├── ApplicationRepository.java
│   │   │   │   └── StudentRepository.java
│   │   │   ├── entity/
│   │   │   │   ├── Course.java
│   │   │   │   ├── Application.java
│   │   │   │   └── Student.java
│   │   │   ├── dto/
│   │   │   │   ├── CourseDTO.java
│   │   │   │   ├── ApplicationRequestDTO.java
│   │   │   │   ├── ApplicationResponseDTO.java
│   │   │   │   ├── ApplicationStatusUpdateDTO.java
│   │   │   │   └── EmailNotificationDTO.java
│   │   │   ├── exception/
│   │   │   └── util/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
├── pom.xml
├── README.md
└── BUILD_GUIDE.md
```

## Troubleshooting

### Issue: Port 8080 already in use
**Solution**: Change port in application.properties:
```properties
server.port=8081
```

### Issue: Database connection error
**Solution**: Verify H2 configuration in application.properties is correct

### Issue: Lombok not working
**Solution**: Ensure you have installed Lombok processor in your IDE or rebuild: `mvn clean install`

### Issue: Dependencies not downloading
**Solution**: Clear Maven cache:
```bash
rm -rf ~/.m2/repository
mvn clean install
```

## Configuration Files

### application.properties
Located in `src/main/resources/application.properties`
- Server configuration
- Database configuration
- JPA/Hibernate settings
- Email SMTP settings
- Logging configuration

### pom.xml
Maven configuration file defining:
- Project metadata
- Dependencies (Spring Boot, JPA, Mail, etc.)
- Build plugins
- Java version (17)

## Running Tests

To run unit tests:
```bash
mvn test
```

To run specific test class:
```bash
mvn test -Dtest=YourTestClassName
```

## Building for Production

### Create JAR
```bash
mvn clean package
```

### Run JAR
```bash
java -jar target/admission-management-system-1.0.0.jar
```

### Create Docker Image (Optional)
Create a `Dockerfile` in project root:
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/admission-management-system-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Build and run:
```bash
docker build -t admission-system:1.0 .
docker run -p 8080:8080 admission-system:1.0
```

## Database Setup

### H2 (Development - Automatic)
- Automatically creates tables on startup
- Data reset on application restart
- Access via `/h2-console`

### MySQL (Production)
Update application.properties:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/admission_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

## Email Configuration

For email notifications to work:
1. Configure SMTP in application.properties
2. For Gmail:
   - Use App Password (not regular password)
   - Enable "Less secure app access"
   - Use smtp.gmail.com:587

## Common Maven Commands

```bash
# Clean build artifacts
mvn clean

# Compile code
mvn compile

# Run tests
mvn test

# Package as JAR
mvn package

# Skip tests while building
mvn clean package -DskipTests

# Run Spring Boot application
mvn spring-boot:run

# Check dependencies
mvn dependency:tree

# Update dependencies
mvn versions:display-dependency-updates
```

## IDE Setup

### IntelliJ IDEA
1. Open project folder
2. IDE will auto-detect Maven project
3. Install Lombok plugin (if not installed)
4. Right-click pom.xml → Maven → Reimport

### Eclipse
1. Import as Existing Maven Project
2. Install m2e plugin
3. Install Lombok plugin
4. Maven → Update Project

### VS Code
1. Install "Extension Pack for Java"
2. Install "Spring Boot Extension Pack"
3. Open folder containing pom.xml

## Performance Tips

- Use production database (MySQL/PostgreSQL) instead of H2 for better performance
- Enable query logging only during development
- Configure connection pooling for production
- Set appropriate JVM heap size: `java -Xmx512m -Xms256m -jar app.jar`

## Next Steps

1. Test API endpoints using Postman or curl
2. Create sample courses
3. Submit test applications
4. Test status updates and email notifications
5. Deploy to production server

---

For more information, see the main [README.md](README.md)
