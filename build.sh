#!/usr/bin/env bash
set -e;

(cd shared; mvn clean package -Dmaven.test.skip)
(cd eureka-server; mvn clean package -Dmaven.test.skip)
(cd forums-service; mvn clean package -Dmaven.test.skip)
(cd gateway-server; mvn clean package -Dmaven.test.skip)
(cd messages-service; mvn clean package -Dmaven.test.skip)
(cd topics-service; mvn clean package -Dmaven.test.skip)
(cd frontend-service; npm install; npm run build)


