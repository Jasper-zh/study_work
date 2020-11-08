package mk4.five;


import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ChatServer extends Thread{

    private Socket s;

    public ChatServer(Socket s) {
        this.s = s;
    }
    @Override
    public void run() {
        try {
            // 接收来自Socket的输入流，即获取一个客户端发来的消息
            BufferedReader br = Tools.getBuffer(s.getInputStream());
            String msg = "";
            while((msg = br.readLine()) != null){
                // 转发给连接进来的list中的每一个客户端
                for(Socket sitem : Server.list){
                    Tools.sendMsg(sitem, s, msg);
                }
            }
        }catch(SocketException e){
            System.err.println("客户端退出:"+Tools.showIp(s.getInetAddress()));
            Server.list.remove(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
