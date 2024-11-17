# Builder
FROM gradle:7.5.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle build --no-daemon

# Runner
FROM openjdk:17-jdk-slim
VOLUME /tmp

ENV POSTGRES_DB=db
ENV POSTGRES_USER=user
ENV POSTGRES_PASSWORD=password

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 3228
CMD ["java", "-jar", "/app.jar"]