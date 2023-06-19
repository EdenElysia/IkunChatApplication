/*import javax.swing.*;
import java.awt.*;

public class IkunChatApplication implements Runnable {

    public static JTextArea chatArea;
    private JFrame frame;
    private JList<String>contactList;
    public static JTextField inputField;

    private JSplitPane splitPane;
    private JScrollPane contactScrollPane;
    private JScrollPane chatScrollPane;
    public static JButton send;
        public static void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            chatArea.append("我：" + message + "\n");
            inputField.setText("");
        }
    }

    public void run()
    {
        frame = new JFrame("QQ Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(150); // 设置分割线位置

        contactList = new JList<>(new String[]{"联系人1", "联系人2", "联系人3"});
        contactScrollPane = new JScrollPane(contactList);
        splitPane.setLeftComponent(contactScrollPane);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatScrollPane = new JScrollPane(chatArea);
        splitPane.setRightComponent(chatScrollPane);

        inputField = new JTextField(30);

        send = new JButton("发送");
        send.addActionListener(new Monitor());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(inputField);
        inputPanel.add(send);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }
}*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;


public class IkunChatApplication implements Runnable  {

    public  JTextArea chatArea;
    private JFrame frame ;
    public  DefaultListModel<String> listModel = new DefaultListModel<>();
    public  JList<String> contactList=new JList<>(listModel);
    public  JTextField inputField;
    public  JButton send;
    public  JTextField addFriendField;
    public  JButton addFriendButton;
    public  String ip;
    public  String username;
    public SendMessage sendMessage;
    private ReceiveMessage receiveMessage;
    private String oldMessage;
    public IkunChatApplication(String ip,String username,String af_bName,String sf_bName) throws IOException {
        frame = new JFrame("Ikun Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel leftPanel = new JPanel(new BorderLayout());
        addFriendButton=new JButton(af_bName);

        JScrollPane contactScrollPane = new JScrollPane(contactList);
        leftPanel.add(contactScrollPane, BorderLayout.CENTER);

        JPanel addFriendPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL; // 设置文本框水平填充
        constraints.insets = new Insets(50, 10, 0, 10); // 设置按钮和文本框的边距
        addFriendField = new JTextField(15);
        addFriendPanel.add(addFriendField, constraints);

        constraints.gridy = 1;
        addFriendPanel.add(addFriendButton, constraints);

        leftPanel.add(addFriendPanel, BorderLayout.SOUTH);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        inputField = new JTextField(30);
        inputField.setText(username+":");
        send = new JButton(sf_bName);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(inputField);
        inputPanel.add(send);


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, chatScrollPane);
        splitPane.setDividerLocation(150); // 设置分割线位置

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        this.ip=ip;
        this.username=username;
        receiveMessage=new ReceiveMessage(this.ip);
    }
    public void addSendButtonListener(ActionListener listener)
    {
        send.addActionListener(listener);
    }
    public void addAddFriendButtonListener(ActionListener listener)
    {
        addFriendButton.addActionListener(listener);
    }



    public void run()
    {
        Main.executor.submit(receiveMessage);
        while (true)
        {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (oldMessage==receiveMessage.getMessage())
                continue;
            oldMessage= receiveMessage.getMessage();
            chatArea.append("\r\n"+oldMessage);

        }
    }

}
