FROM openjdk:17-jdk

WORKDIR /app

COPY target/medheadUser-0.0.1-SNAPSHOT.jar /app/medhead-api-user.jar

EXPOSE 8081

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "medhead-api-user.jar"]