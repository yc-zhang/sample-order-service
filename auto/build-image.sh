#!/usr/bin/env bash

LOCAL_IMAGE_NAME=sample-order-service
LAST_LOCAL_IMAGE_NAME=${LOCAL_IMAGE_NAME}:latest

docker-compose --project-name sample-order-service \
    run --rm _base gradle clean build -x check

docker build -t ${LOCAL_IMAGE_NAME} .
docker tag ${LOCAL_IMAGE_NAME} ${LAST_LOCAL_IMAGE_NAME}
