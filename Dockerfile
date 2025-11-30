FROM openjdk:17-slim AS build

WORKDIR /app

# copy your entire src folder
COPY src ./src

# compile Java files into /app/out
RUN mkdir -p out && \
    javac -cp "src/lib/*" -d out $(find src -name '*.java')

# ----------------------------
# Runtime container
# ----------------------------
FROM openjdk:17-slim

WORKDIR /app

# copy compiled classes
COPY --from=build /app/out ./out

# copy static folder for serving HTML
COPY src/com/airtripe/Static ./static

# copy JAR libraries
COPY src/lib ./lib

# Railway sets PORT automatically
ENV PORT=${PORT}

CMD ["sh", "-c", "java -cp 'out:lib/*' com.airtripe.studymanagement.main.RestServer"]
