import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class ChatClientGUI extends JFrame {
    private JTextArea txtArea;
    private JTextField txtInput;
    private JButton btnSend;
    private ChatClient client;

    public ChatClientGUI() {
        setTitle("🧑‍💻 Máy Trạm Quán Net");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        txtArea = new JTextArea();
        txtArea.setEditable(false);
        add(new JScrollPane(txtArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        txtInput = new JTextField();
        btnSend = new JButton("Gửi");
        bottomPanel.add(txtInput, BorderLayout.CENTER);
        bottomPanel.add(btnSend, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        try {
            client = new ChatClient(new URI("ws://localhost:8887"), this);
            client.connect();
        } catch (Exception e) {
            addMessage("⚠️ Không thể kết nối server!");
        }

        btnSend.addActionListener(e -> sendMessage());
        txtInput.addActionListener(e -> sendMessage());
    }

    public void addMessage(String msg) {
        SwingUtilities.invokeLater(() -> txtArea.append(msg + "\n"));
    }

    private void sendMessage() {
        String msg = txtInput.getText().trim();
        if (!msg.isEmpty() && client != null && client.isOpen()) {
            client.send(msg);
            addMessage("🧑‍💻 Bạn: " + msg);
            txtInput.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatClientGUI().setVisible(true));
    }
}
