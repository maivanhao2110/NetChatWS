# 💬 Ứng dụng Chat Java (Client - Server)

Ứng dụng chat sử dụng giao thức WebSocket viết bằng Java.  
Gồm hai phần: **Server** và **Client**, có thể chạy trên máy cá nhân (localhost).

---

## 🚀 Cách chạy

1️⃣ Cài **Java JDK** (>= 17)  
2️⃣ Tải hoặc clone repo này về  
3️⃣ Giải nén và đảm bảo thư mục `lib/Java-WebSocket-1.5.6.jar` vẫn còn  
4️⃣ Chạy file:

- `run-server.bat` → mở server
- `run-client.bat` → mở client  
  5️⃣ Nhập IP Server (mặc định: `localhost`) và bắt đầu chat 🎉

---

## 📁 Cấu trúc thư mục

NetChatWS/
│
├── CLIENT/
│ ├── ChatClient.java
│ └── ChatClientGUI.java
│
├── SERVER/
│ ├── ChatServer.java
│ └── ChatServerGUI.java
│
├── lib/
│ ├── Java-WebSocket-1.5.6.jar
│ ├── slf4j-api-1.7.36.jar
│ └── slf4j-simple-1.7.36.jar
│
├── run-client.bat # Script biên dịch & chạy Client
├── run-server.bat # Script biên dịch & chạy Server
├── .gitignore # File loại trừ .class, config IDE,...
└── README.md # Hướng dẫn sử dụng & chạy chương trình

---

📌 Tác giả: _Nhóm 6_  
📅 Phiên bản: 1.0
