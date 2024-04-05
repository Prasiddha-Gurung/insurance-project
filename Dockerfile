FROM openjdk:21

WORKDIR /app

ENV DB_USER=insurance_owner
ENV DB_PASSWORD=iAF5ETRDbGZ6
ENV DB_HOST=ep-shy-bread-a2d5caol.eu-central-1.aws.neon.tech
ENV DB_NAME=insurance
ENV DB_SSLMODE=require

COPY ./target/insurance-0.0.1-SNAPSHOT.jar /app/application.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/application.jar"]
