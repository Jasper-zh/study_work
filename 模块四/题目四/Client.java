package mk4.four;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream =null;
        ObjectInputStream objectInputStream =null;
        ObjectOutputStream objectOutputStream = null;
        try {
            //发送连接
            System.out.println("客户端发送请求");
            socket = new Socket("127.0.0.1",8888);
            //等10秒传入对象信息
            Thread.sleep(10000);
            System.out.println("客户端开始发送信息");
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            User user = new User();
            user.setUsername("admin");
            user.setPassword("123456");
            UserMessage userMessage = new UserMessage();
            userMessage.setUser(user);
            //传入userMessage
            objectOutputStream.writeObject(userMessage);
            //接收服务端反馈
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            userMessage = (UserMessage) objectInputStream.readObject();
            System.out.println(userMessage.getState());
            if(userMessage.getState().equals("success")){
                System.out.println("登陆成功");
            }else {
                System.out.println("登陆失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null!=outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null!=inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null!=objectInputStream){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null!=objectOutputStream){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null!=socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
