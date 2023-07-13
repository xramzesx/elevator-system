#!/bin/bash

echo "Build server .jar"
cd server
chmod u+x ./build.sh
./build.sh
cd ..

echo "Run docker compose"
docker compose up