package mk4.five;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{
    // 声明一个容器,用于存储所有Socket对象，用来转发给所有用户
    static List<Socket> list = new ArrayList<>();
    private Socket s;

    public Server(Socket s){
        super();
        this.s = s;
    }
    @Override
    public void run() {
        // 接收客户端发送过来的消息并转发给所有的客户端
        new ChatServer(s).start();
    }

    public static void main(String[] args) throws IOException {
        // 指定端口
        ServerSocket ss = new ServerSocket(8888);
        System.out.println("服务器启动");
        while(true){
            // 等待客户端连接
            Socket s = ss.accept();
            list.add(s);
            Tools.msg("客户端连接成功:"+Tools.showIp(s.getInetAddress()));
            // 对每个Socket多线程处理
            new Server(s).start();
        }
    }
}
