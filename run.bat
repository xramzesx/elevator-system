@echo off

echo Build server .jar
cd server
CALL build.bat
cd ..

echo Run docker compose
docker compose up