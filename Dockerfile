FROM gradle:jdk21 AS build

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts /app/
COPY src /app/src
COPY gradlew /app/
COPY gradlew.bat /app/
COPY gradle /app/gradle

RUN ./gradlew build

FROM openjdk:21

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]