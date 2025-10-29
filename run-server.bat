@echo off
echo === Bien dich SERVER ===
javac -cp ".;lib\Java-WebSocket-1.5.6.jar;lib\slf4j-api-1.7.36.jar;lib\slf4j-simple-1.7.36.jar" SERVER\*.java
if %errorlevel% neq 0 (
    echo Loi khi bien dich SERVER!
    pause
    exit /b
)
echo === Chay SERVER ===
java -cp ".;lib\Java-WebSocket-1.5.6.jar;lib\slf4j-api-1.7.36.jar;lib\slf4j-simple-1.7.36.jar;SERVER" ChatServerGUI
pause
