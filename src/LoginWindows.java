import java.awt.*;

public class LoginWindows  implements  Runnable {

    public static Frame login_window;
    public static Button Login;
    public static Button sign_up;
    public static Label user_label;
    public static Label password_label;
    public static TextField user;
    public static TextField password;


    private int window_high=600;
    private int window_wide=600;

    private int login_button_high=30;
    private int login_button_wide=100;

    private int signup_high=30;
    private int signup_wide=100;

    private int login_location_x=200;
    private int login_location_y=250;

    private int signup_location_x=300;

    private int signup_location_y=250;

    private int u_label_location_x=200;
    private int u_label_location_y=150;

    private int u_label_high=20;

    private int u_label_wide=80;
    private int p_label_location_x=200;

    private int p_label_location_y=200;
    private int p_label_high=20;
    private int p_label_wide=80;

    private int u_field_location_x=300;
    private int u_field_location_y=150;

    private int u_field_high=30;
    private int u_field_wide=200;

    private int p_field_location_x=300;
    private int p_field_location_y=200;
    private int p_field_high=30;
    private int p_field_wide=200;
    // 内部类作为登录按钮的监听器

    public void run()
    {
        login_window=new Frame();
        login_window.setSize(window_high,window_wide);

        Login =new Button("Login");
        Login.setBounds(login_location_x,login_location_y,login_button_wide,login_button_high);

        //创建注册按钮
        sign_up =new Button("sign_up");
        sign_up.setBounds(signup_location_x,signup_location_y,signup_wide,signup_high);

        Login.addActionListener(new Monitor());
        sign_up.addActionListener(new Monitor());

        //创建标签
        user_label=new Label("UserName");
        user_label.setBounds(u_label_location_x,u_label_location_y,u_label_wide,u_label_high);

        password_label=new Label("PassWord");
        password_label.setBounds(p_label_location_x,p_label_location_y,p_label_wide,p_label_high);

        //创建文本框
        user=new TextField();
        user.setBounds(u_field_location_x,u_field_location_y,u_field_wide,u_field_high);

        password=new TextField();
        password.setBounds(p_field_location_x,p_field_location_y,p_field_wide,p_field_high);
        password.setEchoChar('*');

        login_window.setLayout(null);
        login_window.add(Login);
        login_window.add(sign_up);
        login_window.add(user_label);
        login_window.add(password_label);
        login_window.add(user);
        login_window.add(password);

        login_window.setVisible(true);
    }

}
