# Blog Application

A simple blog application built with Spring Boot and modern frontend technologies.

## Technologies Used

- Backend:
  - Spring Boot 3.x
  - Spring Data JPA
  - PostgreSQL
  - Java 17
- Frontend:
  - HTML5
  - Bootstrap 5
  - jQuery
  - SweetAlert2
  - Font Awesome

## Prerequisites

- Docker and Docker Compose
- Git
- AWS Account (for EC2 deployment)

Note: For local development without Docker, you'll also need:
- Java 17 or higher
- Maven
- PostgreSQL

## Local Development Setup

1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd blog
   ```

2. Copy the environment file:
   ```bash
   cp .env.example .env
   ```

3. Update the `.env` file with your database credentials and other configurations.

4. Choose one of the following methods to run the application:

   ### Using Docker Compose (Recommended):
   ```bash
   docker-compose up -d
   ```
   This will:
   - Build the application inside Docker
   - Set up PostgreSQL database
   - Start all services

   ### Manual Setup (Alternative):
   ```bash
   mvn clean package -DskipTests
   java -jar target/*.jar
   ```

The application will be available at `http://localhost:8080`

## AWS EC2 Deployment

1. Launch an EC2 instance:
   - Choose Amazon Linux 2 AMI
   - Select t2.micro (free tier) or larger
   - Configure security group:
     - Allow inbound traffic on port 22 (SSH)
     - Allow inbound traffic on port 8080 (Application)
     - Allow inbound traffic on port 5432 (PostgreSQL - optional, for external DB access)

2. Connect to your EC2 instance and install required software:
   ```bash
   # Update system packages
   sudo yum update -y

   # Install Git
   sudo yum install git -y

   # Install Docker
   sudo yum install docker -y
   sudo service docker start
   sudo usermod -a -G docker ec2-user
   
   # Install Docker Compose
   sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
   sudo chmod +x /usr/local/bin/docker-compose

   # Start Docker service
   sudo systemctl enable docker
   sudo systemctl start docker

   # Log out and log back in for docker group changes to take effect
   exit
   ```

3. Clone and deploy the application:
   ```bash
   # Clone the repository
   git clone <your-repo-url>
   cd blog

   # Set up environment variables
   cp .env.example .env
   nano .env  # Edit the environment variables as needed

   # Build and start the application
   docker-compose up -d --build
   ```

The application will be available at `http://your-ec2-ip:8080`

## Database Setup

### Using Docker Compose (Recommended)

The `docker-compose.yml` file includes PostgreSQL configuration. When you run `docker-compose up -d`, it will:
- Create a PostgreSQL container
- Set up the database with credentials from `.env`
- Create a persistent volume for data storage
- Configure networking between the app and database

### Local PostgreSQL Setup (Alternative)

If you prefer to run PostgreSQL locally:

1. Install PostgreSQL
2. Create a database:
   ```sql
   CREATE DATABASE blogdb;
   ```
3. Update `.env` with your local PostgreSQL credentials

## Features

- Create, Read, Update, and Delete blog posts
- Modern, responsive UI
- Real-time form validation
- Sweet alerts for better user experience
- Mobile-friendly design

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| DB_HOST | Database host | localhost |
| DB_PORT | Database port | 5432 |
| DB_NAME | Database name | blogdb |
| DB_USERNAME | Database username | postgres |
| DB_PASSWORD | Database password | postgres |
| SERVER_PORT | Application port | 8080 |
| SPRING_PROFILES_ACTIVE | Spring active profile | prod |

## Troubleshooting

1. If you can't connect to the application:
   - Check if the security group allows traffic on port 8080
   - Verify the application logs: `docker-compose logs app`

2. If the database connection fails:
   - Check if PostgreSQL is running: `docker-compose logs db`
   - Verify the environment variables in `.env`
   - Ensure the database container is healthy: `docker-compose ps`

3. Common Docker issues:
   - Clear Docker cache: `docker system prune -a`
   - Restart containers: `docker-compose restart`
   - Check container status: `docker-compose ps`
   - View build logs: `docker-compose build --no-cache`

## Maintenance

- View logs: `docker-compose logs -f`
- Restart services: `docker-compose restart`
- Stop application: `docker-compose down`
- Update application:
  ```bash
  git pull
  docker-compose down
  docker-compose up -d --build
  ```

## Build Process

The application uses a multi-stage Dockerfile that:
1. First stage (builder):
   - Uses Maven to build the application
   - Compiles the code and creates the JAR file
2. Second stage (runtime):
   - Uses a slim JDK image
   - Copies only the necessary files
   - Results in a smaller final image

This approach ensures that:
- Build tools are not included in the final image
- The build process is consistent across environments
- The final image is optimized for production
