import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatServer extends WebSocketServer {
    private ChatServerGUI gui;
    private Map<WebSocket, String> clientNames = new HashMap<>();
    private Queue<Message> messageQueue = new ConcurrentLinkedQueue<>();
    private int clientCounter = 1;

    public ChatServer(int port, ChatServerGUI gui) {
        super(new InetSocketAddress(port));
        this.gui = gui;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        String name = "Client-" + clientCounter++;
        clientNames.put(conn, name);
        gui.addLog("✅ " + name + " connected.");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        String name = clientNames.remove(conn);
        gui.addLog("❌ " + name + " disconnected.");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        String name = clientNames.get(conn);
        Message msg = new Message(name, message, conn);
        messageQueue.add(msg);
        gui.addMessageToQueue(msg);
        gui.addLog("📩 " + name + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        gui.addLog("⚠️ Error: " + ex.getMessage());
    }

    @Override
    public void onStart() {
        gui.addLog("🚀 Server started at port " + getPort());
    }

    public void sendMessageToClient(WebSocket conn, String reply) {
        if (conn != null && conn.isOpen()) {
            conn.send("👑 Server: " + reply);
        }
    }

    public static class Message {
        public String sender;
        public String content;
        public WebSocket conn;

        public Message(String sender, String content, WebSocket conn) {
            this.sender = sender;
            this.content = content;
            this.conn = conn;
        }

        public String toString() {
            return sender + ": " + content;
        }
    }
}
