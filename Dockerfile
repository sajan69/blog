# Build stage
FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /build
# Copy the project files
COPY pom.xml .
COPY src ./src
# Build the project
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the built JAR from builder stage
COPY --from=builder /build/target/*.jar app.jar
# Copy the environment file
COPY .env .env
# Expose port 8080
EXPOSE 8080
# Run the application
CMD ["java", "-jar", "app.jar"] 