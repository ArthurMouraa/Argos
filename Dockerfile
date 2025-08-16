FROM  maven:3.9.10-eclipse-temurin-21 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install -DskipTests


FROM eclipse-temurin:21-jre-alpine


COPY --from=build /app/target/spring-boot-2-hello-world-1.0.2-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 9090

CMD ["java", "-jar", "app.jar"]