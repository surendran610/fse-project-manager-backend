FROM openjdk:8
EXPOSE 8099
ADD target/fse-projectmanager-backend.jar fse-projectmanager-backend.jar
ENTRYPOINT ["java","-jar","/fse-projectmanager-backend.jar"]
