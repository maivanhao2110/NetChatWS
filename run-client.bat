@echo off
echo === Biên dịch Client ===
javac -cp ".;lib\Java-WebSocket-1.5.6.jar" CLIENT\*.java
echo === Chạy Client ===
java -cp ".;lib\Java-WebSocket-1.5.6.jar;CLIENT" ChatClientGUI
pause
