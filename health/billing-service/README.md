## Running the Project with Docker

This project includes a multi-stage Docker setup for building and running the Java-based billing service. The provided Dockerfile uses Eclipse Temurin 21 (JDK for build, JRE for runtime) and is configured for container-friendly JVM settings.

### Requirements
- Docker and Docker Compose installed
- No external services or databases required
- No required environment variables by default (see `application.properties` for optional configuration)

### Build and Run Instructions
1. **Build and start the service:**
   ```sh
   docker compose up --build
   ```
   This will build the application using Maven inside the container and start the service.

2. **Accessing the service:**
   - The application exposes port **4001**. It will be available on `localhost:4001` by default.

### Configuration Notes
- The service runs as a non-root user (`appuser`) for improved security.
- No persistent volumes or external dependencies are required.
- If you need to set environment variables, you can uncomment and use the `env_file` section in `docker-compose.yml`.

### Ports
- **4001**: Main application port (as defined in `application.properties` and exposed in the Dockerfile and Compose file)

---

_This section was updated to reflect the current Docker-based setup for building and running the billing service._
