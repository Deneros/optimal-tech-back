FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle

COPY src ./src

RUN ./gradlew build --no-daemon --exclude-task bootJar --exclude-task test

EXPOSE 8080

ENTRYPOINT ["./gradlew", "bootRun", "--no-daemon"]
