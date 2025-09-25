## Running the Patient Service with Docker

This project includes a multi-stage Dockerfile and a Docker Compose configuration for containerized deployment.

### Project-Specific Docker Requirements
- **Base Image:** Uses `eclipse-temurin:21-jdk` for build and `eclipse-temurin:21-jre` for runtime (Java 21).
- **Build Tool:** Maven Wrapper (`mvnw`) is used for building the application inside the container.
- **Ports:** The application runs on port **4000** (as set in `application.properties` and exposed in the Dockerfile and Compose file).
- **User:** Runs as a non-root user (`appuser`) for security.

### Environment Variables
- No required environment variables are specified in the Dockerfile or Compose file by default.
- If you need to set environment variables, you can create a `.env` file and uncomment the `env_file` line in `docker-compose.yml`.

### Build and Run Instructions
1. **Build and start the service:**
   ```sh
   docker compose up --build
   ```
   This will build the image and start the `java-patient-service` container.

2. **Accessing the service:**
   - The service will be available on [http://localhost:4000](http://localhost:4000)

### Special Configuration
- **No external services** (such as databases) are configured by default. If your application requires additional services, update `docker-compose.yml` accordingly.
- **Network:** The service runs on a custom Docker network `patient-net`.

### Exposed Ports
- **4000:** Exposed by the container and mapped to the host for application access.

---

*For further details on the application, see the rest of this README or consult the `HELP.md` file.*
