FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
EXPOSE 8007
ADD ./target/services-bankaforo255-withdrawal-0.0.1-SNAPSHOT.jar bankaforo255-service-withdrawal.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/bankaforo255-service-withdrawal.jar"]