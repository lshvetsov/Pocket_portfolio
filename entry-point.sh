#!/usr/bin/env bash

echo
echo "Using Java Version"
java -version

echo "
Version: $VERSION
Launching: $(ls /opt/app/*.jar)
config: $(ls /opt/app/config/*)
JAVA_OPTS: $(echo $JAVA_OPTS | tr -s" ")
NODE_ID: $(echo $NODE_ID | tr -s" ")
"

exec java $JAVA_OPTS  -jar /opt/app/*.jar $@