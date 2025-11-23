#build
FROM maven:3.9.9 AS build
COPY  pom.xml .
RUN mvn  dependency:go-offline -B
COPY src ./src
RUN mvn -DskipTests clean package

#run
FROM amazoncorretto:23
COPY --from=build target/Back-0.0.1-SNAPSHOT.jar Back.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","Back.jar"]
