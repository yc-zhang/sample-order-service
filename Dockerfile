FROM openjdk:8-jre-alpine
MAINTAINER yc <turalyon.zhangyc@gmail.com>
RUN adduser -D yc-dojo
RUN mkdir /app
WORKDIR /app

ADD ./run.sh /app/
ADD ./build/libs/sample-order-service.jar /app/lib/

RUN chown -R yc-dojo /app
USER yc-dojo
EXPOSE 8080

CMD ["./run.sh", "/app/lib/sample-order-service.jar"]
