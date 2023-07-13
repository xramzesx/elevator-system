#! /bin/bash

if [ -d "./target/" ]
then
	rm -r target/
fi
docker build -t build-elevator-server-jar-image -f Dockerfile.build .
docker create -it --name build-elevator-server-jar build-elevator-server-jar-image bash
docker cp build-elevator-server-jar:/target ./target
# docker rm -f build-elevator-server-jar
