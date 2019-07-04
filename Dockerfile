FROM openjdk:8
EXPOSE 8072
ADD target/client-service.jar /client-service.jar
ENTRYPOINT ["java","-jar","client-service.jar"}