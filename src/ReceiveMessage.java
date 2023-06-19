
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ReceiveMessage implements Runnable {

    public MulticastSocket ms;
    public InetAddress address;
    public DatagramPacket dp;
    public String Message;

    public byte[]bytes;
    public ReceiveMessage(String ip) throws IOException {
        ms=new MulticastSocket(1000);
        address=InetAddress.getByName(ip);
        ms.joinGroup(address);

    }
    public void run()
    {
        bytes=new byte[1024];
        //创建接收对象

        dp=new DatagramPacket(bytes,bytes.length);

        while (true) {

            try {
                ms.receive(dp);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String ip = dp.getAddress().getHostAddress();
            String name = dp.getAddress().getHostName();
            int length = dp.getLength();
            int port = dp.getPort();

            Message=new String(bytes,0,length);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("ip为：" + ip + "的主机" + name + "从端口" + port + "发送了一条数据:" + Message);
           // System.out.println("接收端ip"+address.getHostAddress());

        }
    }
    public String getMessage()
    {
        System.out.println(Message+"调用");
        return Message;
    }
}
