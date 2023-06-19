import java.awt.*;
import java.io.*;


public class FileStream extends Component {

    private  FileInputStream  fis;
    private  FileOutputStream fos;
    public   File file;
    private byte[] bytes;
    public File Ip;
    private char[] array;


    public  FileStream(String name)
    {
        file=new File("D:\\multi-person chatroom\\Password\\"+name+".txt");
        Ip=new File("D:\\multi-person chatroom\\IP\\"+name+".txt");
    }
    public void writeLine(String str,File file) throws IOException {
        fos=new FileOutputStream(file,true);
        bytes=str.getBytes();
        fos.write(bytes);
        fos.close();
    }

    public String readLine(File file) throws IOException
    {
        fis = new FileInputStream(file);
        array=new char[(int)file.length()];
        int pointer,c_pointer=0;
        while ((pointer= fis.read())!=-1)
        {
            array[c_pointer] = (char) pointer;
            c_pointer++;
        }
        String str=String.valueOf(array);
        System.out.println(str);
        fis.close();
        return str;
    }
   /* public String readLine(File file) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String str = stringBuilder.toString();
            System.out.println(str);
            return str;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }*/


    public boolean checkRegistrationStatus() throws IOException
    {
        if(file.createNewFile())
            return Ip.createNewFile();
        return false;
    }


    public boolean checkUserExistence()
    {
        return file.exists()&&file.isFile();
    }
}
