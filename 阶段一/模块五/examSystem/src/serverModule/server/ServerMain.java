package serverModule.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器主类-进行初始化操作和关闭
 */
public class ServerMain {
    private Socket socket;
    private ServerSocket serverSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    @Override
    public String toString() {
        return "ServerMain{" +
                "socket=" + socket +
                ", serverSocket=" + serverSocket +
                ", objectInputStream=" + objectInputStream +
                ", objectOutputStream=" + objectOutputStream +
                '}';
    }
    public ServerMain(ObjectInputStream oi,ObjectOutputStream oo){
        objectInputStream = oi;
        objectOutputStream = oo;
    }
    public ServerMain(){}

    /**
     * 对服务器进行初始化（socket以及输入输出流）
     * @throws IOException
     */
    public void init() throws IOException {
        //创建ServerSocket开放8888端口
        serverSocket = new ServerSocket(8888);
        System.out.println("等待客户端连接......");
        while (true) {
            //阻塞等待客户端连接
            socket = serverSocket.accept();
            System.out.println("客户端连接成功！");
            //初始化对象输入输出流
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("服务器初始化完毕");
            //开启线程
            new ServerFunction(new ServerMain(objectInputStream,objectOutputStream)).start();
        }


    }

    /**
     * 对资源进行释放
     * @throws IOException
     */
    public void close() throws IOException {

        objectOutputStream.close();
        objectInputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端已关闭！");

    }
}
