FROM openjdk:19-slim

WORKDIR /app

COPY target/orderService-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "orderService-0.0.1-SNAPSHOT.jar"]