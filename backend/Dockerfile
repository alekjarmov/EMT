ARG PLATFORM="linux/amd64"

FROM --platform=${PLATFORM} amazoncorretto:17-alpine

WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw
RUN ./mvnw dependency:resolve

COPY src src

CMD ["./mvnw", "spring-boot:run"]