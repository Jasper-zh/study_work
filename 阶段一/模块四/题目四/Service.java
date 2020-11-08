package mk4.four;

import mk4.one.Student;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Service {
    public static void main(String[] args){
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream =null;
        ObjectOutputStream objectOutputStream =null;
        ObjectInputStream objectInputStream = null;
        String username = "admin";
        String password="123456";
        try {
            //1.创建ServerSocket提供端口号
            serverSocket = new ServerSocket(8888);
            System.out.println("服务端开启成功");
            //2.接收客服端Socket
            socket = serverSocket.accept();
            System.out.println("服务端接收请求成功");
            //创建输入流获取客户端传入信息
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            UserMessage userMessage = (UserMessage) objectInputStream.readObject();
            System.out.println("服务端接收到信息");
            //判断信息内容
            String username1 = userMessage.getUser().getUsername();
            String password1 = userMessage.getUser().getPassword();
            if(username1.equals(username) && password1.equals(password)){
                userMessage.setState("success");
            }else {
                userMessage.setState("fail");
            }
            //将信返回给客户端
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(userMessage);
            System.out.println("服务端已发送反馈");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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
            if(null!=inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null!=outputStream){
                try {
                    outputStream.close();
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
            if(null!=serverSocket){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}