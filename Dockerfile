FROM openjdk:17-jdk
WORKDIR /app
COPY build/libs/hello-spring-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]