FROM jetty:9

RUN apt-get update && apt-get install -y openjdk-8-jdk --no-install-recommends && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /usr/src/app
COPY gradle /usr/src/app/gradle/
COPY gradlew /usr/src/app/
COPY settings.gradle /usr/src/app/
COPY build.gradle /usr/src/app/
COPY src /usr/src/app/src/

WORKDIR /usr/src/app
RUN ./gradlew war

COPY build/libs/exampleservlet-1.0.war /var/lib/jetty/webapps/root.war

WORKDIR /var/lib/jetty
EXPOSE 8080
ENTRYPOINT ["/docker-entrypoint.bash"]
CMD ["java","-Djava.io.tmpdir=/tmp/jetty","-jar","/usr/local/jetty/start.jar"]
