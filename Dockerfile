FROM openjdk:11.0.11-jre
MAINTAINER Sergey Maletsky <sergey.maletsky@gmail.com>

RUN apk update && apk add bash curl
RUN addgroup -S gatling && adduser -S gatling -G gatling

WORKDIR /usr/local/app/bin
RUN mkdir -m 0755 -p .

COPY build/libs/*.jar .
COPY docker-entrypoint.sh .

RUN chown -R gatling:gatling /usr/local/app
RUN chmod +x /usr/local/app/bin/docker-entrypoint.sh

ENV PORT 8080
EXPOSE $PORT

CMD ["/usr/local/app/bin/docker-entrypoint.sh"]
