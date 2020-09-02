FROM openjdk:14-jdk-alpine
LABEL manteiner="xroigmartin@gmail.com"
EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE} ecm.jar
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/ecm.jar" ]