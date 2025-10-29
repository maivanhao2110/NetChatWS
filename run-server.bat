@echo off
echo === Biên dịch Server ===
javac -cp ".;lib\Java-WebSocket-1.5.6.jar" SERVER\*.java
echo === Chạy Server ===
java -cp ".;lib\Java-WebSocket-1.5.6.jar;SERVER" ChatServerGUI
pause
