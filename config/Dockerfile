FROM openjdk:8u275-jre-slim-buster
EXPOSE 8760
ENV env_val native
COPY dependency/config*.jar /app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${env_val}", "/app.jar" ]
