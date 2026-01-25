FROM eclipse-temurin:21
LABEL maintainer="Adoptium"
WORKDIR /booksco
COPY target/demo-0.0.1-SNAPSHOT.jar /booksco/dockerA1.jar
ENTRYPOINT ["java", "-jar", "dockerA1.jar"]
