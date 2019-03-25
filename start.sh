#!/usr/bin/env bash

exec java \
    -Ddd.integration.akka-http.enabled=true \
    -javaagent:dd-java-agent.jar \
    -Ddd.writer.type=LoggingWriter \
    -Ddd.service.name=explore_scala_test \
    -cp "./test-datadog-assembly-0.1.jar" \
    Server