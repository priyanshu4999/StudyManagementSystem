FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY src ./src

RUN mkdir -p out && \
    javac -cp "src/lib/*" -d out $(find src -name '*.java')

# ----------------------------
# Runtime container
# ----------------------------
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/out ./out
COPY src/com/airtripe/Static ./static
COPY src/lib ./lib

ENV PORT=${PORT}

CMD ["sh", "-c", "java -cp 'out:lib/*' com.airtripe.studymanagement.main.RestServer"]
