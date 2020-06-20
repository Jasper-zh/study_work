package mk4.five;


import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class MsgReceiver extends Thread{

    private Socket s;


    public MsgReceiver(Socket s) {
        super();
        this.s = s;
    }

    @Override
    public void run() {
        try {
            // 获取基于Socket输入流，接收消息
            BufferedReader br = Tools.getBuffer(s.getInputStream());
            String msg = "";
            while((msg = br.readLine()) != null){
                Tools.msg(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
