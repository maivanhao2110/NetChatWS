import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class ChatServerGUI extends JFrame {
    private JTextArea logArea;
    private DefaultListModel<ChatServer.Message> queueModel;
    private JList<ChatServer.Message> queueList;
    private JTextField replyField;
    private JButton btnSend;
    private ChatServer server;

    public ChatServerGUI() {
        setTitle("💻 Quán Net - Máy Chủ");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Khung log
        logArea = new JTextArea();
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Danh sách hàng đợi
        queueModel = new DefaultListModel<>();
        queueList = new JList<>(queueModel);
        add(new JScrollPane(queueList), BorderLayout.WEST);
        queueList.setPreferredSize(new Dimension(250, 0));
        queueList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Khung gửi phản hồi
        JPanel bottomPanel = new JPanel(new BorderLayout());
        replyField = new JTextField();
        btnSend = new JButton("Gửi phản hồi");
        bottomPanel.add(replyField, BorderLayout.CENTER);
        bottomPanel.add(btnSend, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        btnSend.addActionListener(e -> sendReply());

        // Khởi động server
        server = new ChatServer(8887, this);
        server.start();
    }

    public void addLog(String msg) {
        SwingUtilities.invokeLater(() -> logArea.append(msg + "\n"));
    }

    public void addMessageToQueue(ChatServer.Message msg) {
        SwingUtilities.invokeLater(() -> queueModel.addElement(msg));
    }

    private void sendReply() {
        ChatServer.Message selected = queueList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Chọn tin nhắn cần phản hồi!");
            return;
        }
        String reply = replyField.getText().trim();
        if (reply.isEmpty())
            return;

        server.sendMessageToClient(selected.conn, reply);
        addLog("💬 Reply to " + selected.sender + ": " + reply);
        replyField.setText("");
        queueModel.removeElement(selected);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatServerGUI().setVisible(true));
    }
}
