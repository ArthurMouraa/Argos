FROM eclipse-temurin:21
LABEL maintainer="arthurmoura090@gmail.com"
WORKDIR /app
COPY target/argoss-0.0.1-SNAPSHOT.jar /app/argoss-projeto.jar
ENTRYPOINT ["java", "-jar", "argoss-projeto.jar"]
