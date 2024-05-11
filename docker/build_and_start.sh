#!/usr/bin/env bash

set -e

docker-compose down -v
docker-compose rm -v

(cd ../ && mvn clean install -DskipTests)

docker-compose build
docker-compose up --force-recreate

docker-compose down -v