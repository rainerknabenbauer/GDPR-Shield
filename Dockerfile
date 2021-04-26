FROM openjdk:11
COPY . /usr/src/gdpr
WORKDIR /usr/src/gdpr
RUN ./gradlew bootJar
EXPOSE 44301
CMD java -jar ./build/libs/gdpr-0.0.1-SNAPSHOT.jar