FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/scottj425/JavaRestCounter.git

FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app
COPY --from=clone /app/JavaRestCounter /app
RUN  mvn install

FROM openjdk:8-jre-alpine
WORKDIR /app
EXPOSE 4567
COPY --from=build /app/target/webserv-1.0-SNAPSHOT-jar-with-dependencies.jar /app
RUN echo '#! /bin/sh' > webserv
RUN echo 'java -jar webserv-1.0-SNAPSHOT-jar-with-dependencies.jar' >> webserv
RUN chmod a+x webserv