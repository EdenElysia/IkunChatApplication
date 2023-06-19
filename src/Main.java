
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static ExecutorService executor;
    private static int nThread=16;
    public static  void main(String[] args) throws IOException
    {

        executor= Executors.newFixedThreadPool(nThread);

        LoginWindows loginWindows=new LoginWindows();

        executor.submit(loginWindows);

    }

}