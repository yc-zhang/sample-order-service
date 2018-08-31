#!/usr/bin/env bash
VERSION=${BUILD_ID} # jenkins build id

LOCAL_IMAGE_NAME=sample-order-service
LAST_LOCAL_IMAGE_NAME=${LOCAL_IMAGE_NAME}:latest
REMOTE_IMAGE_NAME=625190582435.dkr.ecr.ap-northeast-2.amazonaws.com/${LOCAL_IMAGE_NAME}

docker-compose --project-name sample-order-service \
    run --rm _base gradle clean build -x check

docker build -t ${LOCAL_IMAGE_NAME} .

docker tag ${LOCAL_IMAGE_NAME} ${REMOTE_IMAGE_NAME}:latest
docker tag ${LOCAL_IMAGE_NAME} ${REMOTE_IMAGE_NAME}:${VERSION}

#wired
#docker push ${REMOTE_IMAGE_NAME}:${VERSION}
#docker push ${REMOTE_IMAGE_NAME}:latest
