package clientModule.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 编程实现客户端的初始化和关闭操作
 */
public class ClientMain {
    /**
     * 自定义成员变量记录Socket和流对象
     */
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    public ObjectOutputStream getOos() {
        return objectOutputStream;
    }
    public ObjectInputStream getOis() {
        return objectInputStream;
    }

    /**
     * 自定义成员方法实现客户端的初始化操作
     */
    public void clientInit() throws IOException {
        // 1.创建Socket类型的对象并指定服务器的通信地址和端口号
        socket = new Socket("localhost", 8888);
        // 2.使用输入输出流通信
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("连接服务器成功！");
    }

    /**
     * 自定义成员方法实现客户端的关闭操作
     */
    public void clientClose() throws IOException {
        // 3.关闭Socket并释放有关的资源
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();
        System.out.println("客户端成功关闭！");
    }

}
