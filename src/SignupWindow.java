import java.awt.*;

public class SignupWindow implements Runnable {

/*****************************************Public Variable*******************************************************************/
    public static Frame signup_window;
    public static Button cancel;
    public static Button sure;

    public static Label u_label;
    public static Label p_label;

    public static TextField u_field;
    public static TextField p_field;
    private int window_high = 600;
    private int window_wide = 600;

    private int u_label_location_x = 200;
    private int u_label_location_y = 150;

    private int u_label_high = 20;

    private int u_label_wide = 80;
    private int p_label_location_x = 200;

    private int p_label_location_y = 200;
    private int p_label_high = 20;
    private int p_label_wide = 80;

    private int u_field_location_x = 300;
    private int u_field_location_y = 150;

    private int u_field_high = 30;
    private int u_field_wide = 200;

    private int p_field_location_x = 300;
    private int p_field_location_y = 200;
    private int p_field_high = 30;
    private int p_field_wide = 200;
    private int cancel_button_high = 30;
    private int cancel_button_wide = 100;

    private int sure_high = 30;
    private int sure_wide = 100;

    private int cancel_location_x = 200;
    private int cancel_location_y = 250;

    private int sure_location_x = 300;

    private int sure_location_y = 250;

    public static int count=0;

    public static String ip;
    public SignupWindow()
    {
        count++;
    }
    public String SignIp()
    {
        return "224.0.0."+count;
    }
    public void run() {
        signup_window = new Frame();
        signup_window.setSize(window_high, window_wide);

        cancel = new Button("cancel");
        cancel.setBounds(cancel_location_x, cancel_location_y, cancel_button_wide, cancel_button_high);

        //创建注册按钮
        sure = new Button("sure");
        sure.setBounds(sure_location_x, sure_location_y, sure_wide, sure_high);

        cancel.addActionListener(new Monitor());
        sure.addActionListener(new Monitor());
        //创建标签
        u_label = new Label("UserName");
        u_label.setBounds(u_label_location_x, u_label_location_y, u_label_wide, u_label_high);

        p_label = new Label("PassWord");
        p_label.setBounds(p_label_location_x, p_label_location_y, p_label_wide, p_label_high);

        //创建文本框
        u_field = new TextField();
        u_field.setBounds(u_field_location_x, u_field_location_y, u_field_wide, u_field_high);

        p_field = new TextField();
        p_field.setBounds(p_field_location_x, p_field_location_y, p_field_wide, p_field_high);
        p_field.setEchoChar('*');

        signup_window.setLayout(null);
        signup_window.add(cancel);
        signup_window.add(sure);
        signup_window.add(u_label);
        signup_window.add(p_label);
        signup_window.add(u_field);
        signup_window.add(p_field);
        signup_window.setVisible(true);
        ip=SignIp();
    }
}

