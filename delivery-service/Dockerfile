FROM openjdk:8u275-jre-slim-buster
EXPOSE 8766
ENV env_val local
ENV spring_cloud_config_uri http://localhost:31175/config-server/
COPY dependency/delivery*.jar /app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${env_val}", "-Dspring.cloud.config.uri=${spring_cloud_config_uri}", "/app.jar" ]



