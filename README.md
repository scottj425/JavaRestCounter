# JavaRestCounter

## Docker Instructions
This application can be built and ran via Docker as well. Follow the steps below to run with Docker.

* `docker build . -t sjohnson/webserv`
* `docker run -it -p4567:4567 sjohnson/webserv ./webserv`

## Build Instructions without Docker
### Requirements
* maven 3.6.3
* Java 1.8

### Buid Instructions
* Clone repo
* in root directory of repo run `mvn install`
* in target directory run `java -jar  webserv-1.0-SNAPSHOT-jar-with-dependencies.jar
`

### Usage
This application will listen for http requests on port `4567`

Send an `HTTP POST` request to `http://localhost:4567/message`
The body of the post should be JSON and have the following format:
```
{
    "id": string,
    "message": string
}
```

This will return a count of all words received by the endpoint. The application will ignore duplicate ids.
