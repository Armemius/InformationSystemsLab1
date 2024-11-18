# Builder
FROM gradle:7.5.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

ENV HOST=db
ENV POSTGRES_DB=lab_1_is
ENV POSTGRES_USER=spring
ENV POSTGRES_PASSWORD=sample_password

RUN gradle build --no-daemon

# Runner
FROM openjdk:17-jdk-slim
VOLUME /tmp

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 3228
EXPOSE 5432
CMD ["java", "-jar", "/app.jar"]