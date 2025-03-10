FROM alpine:3.21.3
WORKDIR /app
COPY target/demo /app
RUN apk add gcompat
CMD ./demo
