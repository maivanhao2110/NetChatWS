import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class ChatClient extends WebSocketClient {
    private ChatClientGUI gui;

    public ChatClient(URI serverUri, ChatClientGUI gui) {
        super(serverUri);
        this.gui = gui;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        gui.addMessage("✅ Kết nối thành công đến server!");
    }

    @Override
    public void onMessage(String message) {
        gui.addMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        gui.addMessage("❌ Mất kết nối: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        gui.addMessage("⚠️ Lỗi: " + ex.getMessage());
    }
}
