# PARCEL MANAGEMENT SYSTEM
## Spring Boot Backend (Courier/Parcel Tracking)

---

## 📋 PROJECT OVERVIEW

Backend service for managing courier/parcel receipts with a REST API and H2 in-memory database.

---

## 🛠️ TECHNOLOGY STACK

**Backend:**
- Java 17
- Spring Boot 4.0.2
- Spring Web MVC
- Spring Data JPA
- H2 Database (in-memory)
- Maven

**Frontend (optional):**
- React (not included in this repository)
- CORS is enabled for http://localhost:3000

---

## 🗂️ PROJECT STRUCTURE

```
courier_tracking/
├── .github/
│   └── workflows/
│       └── build.yml
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── courier_tracking/
│   │   │   │   └── CourierTrackingApplication.java
│   │   │   └── parcelmanagement/
│   │   │       ├── controller/ParcelController.java
│   │   │       ├── model/Parcel.java
│   │   │       ├── repository/ParcelRepository.java
│   │   │       └── service/
│   │   │           ├── ParcelService.java
│   │   │           └── impl/ParcelServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/com/example/courier_tracking/CourierTrackingApplicationTests.java
└── target/ (build output)
```

---

## 🚀 INSTALLATION AND SETUP

### Prerequisites
- Java 17
- Maven 3.9+ (or use the Maven Wrapper)

### Run locally
1. Navigate to the backend folder:
   ```
   cd d:\courier_tracking\courier_tracking
   ```
2. Start the application:
   ```
   mvn spring-boot:run
   ```
   Or use the wrapper:
   ```
   .\mvnw.cmd spring-boot:run
   ```

### Base URLs
- API: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console

---

## ⚙️ H2 DATABASE CONFIGURATION

Configured in `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:h2:mem:parceldb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

**H2 Console Login:**
- JDBC URL: `jdbc:h2:mem:parceldb`
- Username: `sa`
- Password: *(empty)*

To change the server port, update:
```
server.port=8080
```

---

## 📡 API ENDPOINT DOCUMENTATION

**Base path:** `/api/parcels`

| Method | Endpoint              | Description           |
|--------|----------------------|----------------------|
| POST   | /api/parcels         | Create new parcel    |
| GET    | /api/parcels         | Get all parcels      |
| GET    | /api/parcels/{id}    | Get parcel by ID     |
| PUT    | /api/parcels/{id}    | Update parcel        |
| DELETE | /api/parcels/{id}    | Delete parcel        |

**Parcel JSON model:**
```
{
  "senderName": "John Doe",
  "receiverName": "Jane Smith",
  "parcelDescription": "Books",
  "receivedDate": "2026-01-31",
  "status": "RECEIVED",
  "contactNumber": "1234567890"
}
```

---

## 🐳 DOCKER INSTRUCTIONS

1. Build the JAR:
   ```
   mvn -DskipTests package
   ```
2. Build the image:
   ```
   docker build -t courier-tracking:latest .
   ```
3. Run the container:
   ```
   docker run -p 8080:8080 courier-tracking:latest
   ```

---

## 🧪 TESTING

Run tests with:
```
mvn test
```

CI uses:
```
mvn -B verify
```

---

## 💡 DEVELOPMENT TIPS (HOT RELOAD)

- `spring-boot-devtools` is included for automatic restarts on classpath changes.
- Use your IDE's automatic build/reload for the fastest feedback cycle.
- H2 is in-memory; data resets on every restart.

---

## ✅ CODE QUALITY AND SONAR (SONARCLOUD/SONARQUBE)

GitHub Actions workflow: `.github/workflows/build.yml`

Sonar configuration:
- Organization: `23suca33-bca26`
- Project key: `23suca33-bca26_courier_backend`

The workflow runs:
```
mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar
```

Required secret:
- `SONAR_TOKEN` (set in GitHub repository secrets)

---

## 🔀 PULL REQUESTS

- Open a PR against `main`.
- The GitHub Actions workflow runs on PR open/synchronize/reopen.
- Ensure the build and Sonar analysis succeed before merging.

---

## ⚡ DEPLOYMENT CHALLENGES & SOLUTIONS

### 1️⃣ Challenge: node_modules committed to GitHub

**Problem:**
- The node_modules folder was pushed to the repository, causing:
  - Huge repo size
  - Permission errors during CI/CD
  - Build failures on Vercel (exit code 126)

**Solution:**
- Removed node_modules from Git tracking:
  ```
  git rm -r --cached node_modules
  ```
- Added node_modules to .gitignore
- Redeployed so Vercel installs dependencies automatically

---

### 2️⃣ Challenge: Vercel Build Failed (npm run build exited with 126)

**Problem:**
- Vercel could not execute react-scripts due to incorrect permissions from committed dependencies.

**Solution:**
- Cleaned repository
- Allowed CI environment to install fresh dependencies
- Ensured react-scripts exists in package.json

---

### 3️⃣ Challenge: Backend container not responding (ERR_EMPTY_RESPONSE)

**Problem:**
- Docker container was running but not exposing the service correctly.

**Solution:**
- Verified backend was listening on 0.0.0.0
- Ensured correct port mapping:
  ```
  docker run -p 8080:8080 backend
  ```
- Confirmed service availability via browser and logs

---

### 4️⃣ Challenge: GitHub Organization Requirement

**Problem:**
- Guide required project repositories to be under a GitHub organization instead of a personal account.

**Solution:**
- Created GitHub Organization
- Transferred frontend and backend repositories
- Updated Vercel and CI connections to the organization repos

---

### 5️⃣ Challenge: CI/CD Repo Links Outdated

**Problem:**
- After transferring repositories, deployment platforms still pointed to old repo URLs.

**Solution:**
- Reconnected Vercel to the organization repo
- Updated Render / CI repo links
- Triggered fresh deployments

---

### 6️⃣ Challenge: Environment Variables Missing in Production

**Problem:**
- Application worked locally but failed in production due to missing .env values.

**Solution:**
- Added environment variables in Vercel dashboard
- Ensured .env is ignored in Git
- Used process.env in code

---

### 7️⃣ Challenge: Port Conflicts During Docker Deployment

**Problem:**
- Multiple services attempted to use the same port.

**Solution:**
- Assigned unique ports
- Used Docker Compose for service orchestration

---

### 8️⃣ Challenge: Large Build Time & Dependency Mismatch

**Problem:**
- Inconsistent builds due to different Node versions.

**Solution:**
- Added Node version specification:
  ```json
  "engines": {
    "node": "18.x"
  }
  ```
- Ensured local and CI environments matched

---

## � VERCEL DEPLOYMENT

### Frontend Deployment (with Vercel)

1. Push frontend code to GitHub organization repo
2. Connect Vercel to the repository:
   - Go to https://vercel.com/dashboard
   - Import project from GitHub
   - Select organization repo
3. Configure environment variables in Vercel dashboard
4. Deploy automatically on every push to `main`

### Deployment with Custom Domain

1. In Vercel dashboard, go to Settings → Domains
2. Add your custom domain
3. Follow DNS configuration instructions
4. Vercel automatically provisions SSL certificate

---

## �📞 PORTS USED

- Backend/API: **8080**
- H2 Console: **8080/h2-console**
- Frontend (optional): **3000**
