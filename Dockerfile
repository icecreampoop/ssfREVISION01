## FIRST CONTAINER
FROM maven:3-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src src

RUN chmod a+x /app/mvnw
RUN mvn package -Dmaven.test.skip=true

## SECOND CONTAINER (another server)
FROM maven:3-eclipse-temurin-21

WORKDIR /app_run

COPY --from=builder /app/target/ssfrevision01-0.0.1-SNAPSHOT.jar .

# this is for documentation -- so you rmb what env variables you have to pass in (during docker run, or in Railway server variables)
# ENV API_KEY=placeholder
ENV PORT=8080
EXPOSE ${PORT}

# HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 CMD curl http://127.0.0.1:${PORT}/healthz || exit 1

# ASSIGNING SERVER_PORT TO PORT VARIABLE ALLOWS SERVER (e.g. RAILWAY) TO OVERRIDE THE VARIABLE TO THEIR DESIGNATED PORT
ENTRYPOINT SERVER_PORT=${PORT} java -jar /app_run/ssfrevision01-0.0.1-SNAPSHOT.jar