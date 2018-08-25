#!/bin/bash -eu

DOCKER_COMPOSE_CMD="docker-compose --project-name sample-order-service"

${DOCKER_COMPOSE_CMD} run --rm dev gradle clean check
${DOCKER_COMPOSE_CMD} kill api
${DOCKER_COMPOSE_CMD} rm -fv api
