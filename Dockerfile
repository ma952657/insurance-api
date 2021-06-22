## BUILD EXECUTABLE stage 1 ##
FROM openjdk:8-jdk AS builder
WORKDIR /usr/build
COPY src src
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
RUN ./mvnw package -DskipTests

## EXECUTE APPLICATION stage 2 ##
FROM openjdk:8-jre
WORKDIR /usr/app
COPY --from=builder /usr/build/target/driver-api.jar driver-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","driver-api.jar"]