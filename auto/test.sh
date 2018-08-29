#!/bin/bash -eu

echo "let's go"

DOCKER_COMPOSE_CMD="docker-compose --project-name sample-order-service"

${DOCKER_COMPOSE_CMD} run -u root --rm dev gradle clean check
${DOCKER_COMPOSE_CMD} kill dev
${DOCKER_COMPOSE_CMD} rm -fv dev
