import java.io.IOException;
import java.net.DatagramPacket;

import java.net.InetAddress;
import java.net.*;

public class SendMessage implements Runnable{

    public MulticastSocket ms;
    public InetAddress address;
    int port=1000;
    public DatagramPacket packet;
    public SendMessage(String ip) throws IOException
    {
        ms=new MulticastSocket();
        address=InetAddress.getByName(ip);
    }
    public void setMessage(String Message)
    {
        byte[]bytes=Message.getBytes();
        packet=new DatagramPacket(bytes,bytes.length,address,port);
    }

    public void run()
    {
            try {
                ms.send(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        ms.close();
    }

}
