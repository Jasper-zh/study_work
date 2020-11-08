package mk4.five;

import java.io.BufferedReader;
import java.net.Socket;

public class ClientB {
    public static void main(String[] args) throws Exception{
        Socket s = new Socket("localhost",8888);

        // 接收消息为多线程
        new MsgReceiver(s).start();

        // 从控制台接收消息发送给服务器(单线程)
        BufferedReader br = Tools.getBuffer(System.in);
        String msg = "";
        while((msg = br.readLine()) != null){
            Tools.sendMsg2(s, msg);
        }
    }
}
