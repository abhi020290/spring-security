FROM openjdk:8-jdk-alpine
EXPOSE 8081
ENV CC_APP_HOME /usr/src/app
ARG JAR_FILE=target/spring-boot-authorization-roles*.jar
COPY $JAR_FILE $CC_APP_HOME/spring-boot-authorization-roles.jar
WORKDIR $CC_APP_HOME
ENTRYPOINT ["java" , "-jar" , "spring-boot-authorization-roles.jar"]


