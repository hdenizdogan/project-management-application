FROM ubuntu:latest

MAINTAINER Hasan Deniz Dogan "hasandenizdogan@gmail.com"

ENV version=aws-db-usage
ENV dbuser=postgres
ENV dbpass=password
ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.crrusd4jrfvr.us-east-2.rds.amazonaws.com:5432/postgres

WORKDIR /usr/local/bin

RUN apt-get update && apt-get install openjdk-11-jdk -y

ADD target/project-management.jar .

ENTRYPOINT ["java","-jar","project-management.jar"]