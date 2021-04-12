FROM maven as builder
COPY . /code/
WORKDIR /code
RUN mvn package

FROM openjdk:11-jre
COPY target/*.jar /usr/app/
WORKDIR /usr/app
CMD [ "java", "-jar", "gameweb-0.0.1-SNAPSHOT.jar" ] 