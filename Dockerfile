FROM maven:3.8.4-openjdk-17 as maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package -DskipTests


FROM openjdk:17-jdk-slim
ARG JAR_FILE=app.jar
WORKDIR /opt/app
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java","-jar","app.jar"]