import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.util.HashMap;

public class Monitor implements ActionListener {

public IkunChatApplication ikun;
public static HashMap<String,IkunChatApplication>prison=new HashMap<>();
    @Override
    public void actionPerformed(ActionEvent e) {
/*********************************************转注册页面*****************************************************************/
        if (e.getSource()==LoginWindows.sign_up)
        {
            SignupWindow signupWindow = new SignupWindow();
            Main.executor.submit(signupWindow);
        }
/********************************************检查账户和密码是否存在*************************************************************/
        else if (e.getSource()==LoginWindows.Login)
        {
            String check=LoginWindows.user.getText();
            FileStream fileStream=new FileStream(check);
            if (fileStream.checkUserExistence())
            {
                try {     //检查密码
                    if (LoginWindows.password.getText().equals(fileStream.readLine(fileStream.file)))
                    {
                        JOptionPane.showMessageDialog(null,"哇，贞德是泥鸭，登录成功");
                        ikun=new IkunChatApplication(fileStream.readLine(fileStream.Ip),LoginWindows.user.getText(),LoginWindows.user.getText(),LoginWindows.user.getText());
                        prison.put(LoginWindows.user.getText(),ikun);
                        SendButtonListener sendButtonListener=new SendButtonListener();
                        addFriendButtonListener addFriendButtonListener=new addFriendButtonListener();

                        ikun.addSendButtonListener(sendButtonListener);
                        ikun.addAddFriendButtonListener(addFriendButtonListener);


                        System.out.println(ikun.ip);
                        Main.executor.submit(ikun);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"错了啦，都是你害的");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"你是一个一个一个.....未找到该用户");
            }
        }
/*******************************************注册账户********************************************************************/
        else if (e.getSource()==SignupWindow.sure)
        {
            //先创建账户
            String FileName=SignupWindow.u_field.getText();
            FileStream fileStream=new FileStream(FileName);

            //创建用户同名文件
            try {
                if (fileStream.checkRegistrationStatus())
                {
                    JOptionPane.showMessageDialog(null,"注册成功");
                    try {
                        fileStream.writeLine(SignupWindow.p_field.getText(),fileStream.file);
                        fileStream.writeLine(SignupWindow.ip,fileStream.Ip);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    SignupWindow.signup_window.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"你干嘛，哎哟，有人啦");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource()==SignupWindow.cancel)
        {
            SignupWindow.signup_window.dispose();
        }

    }
    class SendButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            prison.get(e.getActionCommand()).sendMessage.setMessage(prison.get(e.getActionCommand()).inputField.getText());
            Main.executor.submit(prison.get(e.getActionCommand()).sendMessage);
            prison.get(e.getActionCommand()).chatArea.append("\r\n"+prison.get((e.getActionCommand())).inputField.getText());
            prison.get(e.getActionCommand()).inputField.setText(e.getActionCommand()+":");
        }
    }
    class addFriendButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try {
                prison.get(e.getActionCommand()).sendMessage=new SendMessage(prison.get(e.getActionCommand()).addFriendField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
