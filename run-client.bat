@echo off
echo === Bien dich CLIENT ===
javac -cp ".;lib\Java-WebSocket-1.5.6.jar;lib\slf4j-api-1.7.36.jar;lib\slf4j-simple-1.7.36.jar" CLIENT\*.java
if %errorlevel% neq 0 (
    echo Loi khi bien dich CLIENT!
    pause
    exit /b
)
echo === Chay CLIENT ===
java -cp ".;lib\Java-WebSocket-1.5.6.jar;lib\slf4j-api-1.7.36.jar;lib\slf4j-simple-1.7.36.jar;CLIENT" ChatClientGUI
pause
