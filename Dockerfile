# Stage 1: Build the app with Gradle
FROM gradle:jdk21-ubi AS builder
WORKDIR /app
COPY . .
RUN gradle bootJar -x test --no-daemon

# Stage 2: Create a minimal JDK runtime
FROM eclipse-temurin:21 AS jre-build
RUN $JAVA_HOME/bin/jlink \
         --add-modules java.base,java.desktop,java.logging,java.management,jdk.unsupported,java.naming,java.security.jgss,java.instrument,jdk.crypto.ec,java.security.sasl \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime

# Stage 3: Final runtime image
FROM debian:buster-slim
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Copy slim runtime
COPY --from=jre-build /javaruntime $JAVA_HOME

# Copy the built jar using a wildcard, then rename
COPY --from=builder /app/build/libs/*.jar /opt/app/
RUN find /opt/app -name "*.jar" -exec mv {} /opt/app/openaiservice.jar \;

ENV JAVA_OPTS="-Dserver.port=$PORT"

# Run the app
CMD sh -c 'java $JAVA_OPTS -jar /opt/app/openaiservice.jar'

