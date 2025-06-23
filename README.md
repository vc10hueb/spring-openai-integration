# Open AI ChatGPT Spring Boot Integration

This base application provides a spring boot application that integrates with Chat GPT.
The core technologies here are Spring Boot, Gradle, Docker, Docker Compose, GCP
The application has basic auth, rate limiting, automatic request retry
The image is production ready from a security and disk space perspective. See my article on Image
sizing I take this a few steps further in my
implementation https://www.linkedin.com/pulse/java-production-images-vincent-hueber-u68te/?trackingId=To1Vv%2FZpSZuUkTmezBHgYQ%3D%3D

## Prerequisites to run Locally

Obtain an Open AI API Key and store as an ENV variable `OPENAI_API_KEY`, `BASIC_AUTH_USERNMAE`,
`BASIC_AUTH_PASSWORD`

## Build and Run Local

### Build and Run on Local Machine

`./gradlew bootJar` Generates the JAR file in your build directory.

`java -jar .\build\libs\openaiservice-0.0.1-SNAPSHOT.jar` Executes the JAR file on your local
machine.

### Build and Run Development

In order to enable a positive development experience the development env does not replicate the
cloud deployment environments. Docker Compose is an essnetial tool in standing up your development
env.
See https://www.linkedin.com/pulse/hot-reloading-containerized-applications-vincent-hueber-ssu6e/?trackingId=%2FOL6J5HOQe6CW7%2F%2BJllAMQ%3D%3D
my article on hot reloading in IntelliJ.

`docker compose up`

`docker compose down`

### Build and Run Docker Deployment

This will run the project as a production packaged image locally. The same Dockerfile used by GCPr.

`docker build -t api .`
` docker run -e PORT=8080 -p 8080:8080 -e OPENAI_API_KEY= -e BASIC_AUTH_USERNMAE= -e BASIC_AUTH_PASSWORD=`

## Build and Run in Google Cloud

A `cloudbuild.yaml` has been added to the file. The file is integrated with a volume of type Secret
that holds the value for your cloud deploy `OPENAI_API_KEY`, `BASIC_AUTH_USERNAME`,
`BASIC_AUTH_PASSWORD`.
Exact deployment instructions are not included but I assure you if you take the time to learn GCPr
this project will get you 99% of the way there.

## Curl Requests

Base 64 encode your username and password from your ENV variables.

curl --location 'http://localhost:8080/api/chat/image' \
--header 'Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=' \
--form 'prompt="What type of fish is in this image?"' \
--form 'image=@"/D:/fishnet/fishnetapi/src/main/resources/static/IMG_8645.JPG"'

curl --location --request GET 'http://localhost:8080/api/chat/textPrompt' \
--header 'Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=' \
--form 'prompt="What type of fish is in this image?"'
