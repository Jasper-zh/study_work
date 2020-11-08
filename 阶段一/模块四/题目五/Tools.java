package mk4.five;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

public class Tools {
    // 将接收过来的消息显示出来
    public static void msg(Object msg){
        System.out.println(msg);
    }
    // 由InetAddress 地址 获取主机ip地址
    public static String showIp(InetAddress ip){
        return ip.getHostAddress();
    }
    // 将字节输入流包装为字符输入流
    public static BufferedReader getBuffer(InputStream is){
        return new BufferedReader(new InputStreamReader(is));
    }
    /**
     * 将s发送的消息转发给sitem(服务端转发)
     * @param sitem 消息接收方
     * @param s 消息发送发
     * @param msg
     * @throws IOException
     */
    public static void sendMsg(Socket sitem, Socket s, String msg) throws IOException {
        PrintWriter pw = new PrintWriter(sitem.getOutputStream());
        pw.println(msgFmt(s,msg));
        pw.flush();
    }

    // 格式化消息内容
    public static String msgFmt(Socket s, String msg){
        return s.getInetAddress().getHostName() +"【"+fmtSysTime()+"】:"+msg;
    }
    // 格式化时间
    public static String fmtSysTime(){
        return DateFormat.getTimeInstance().format(new Date());
    }
    // 发送消息(客户端发送)
    public static void sendMsg2(Socket s, String msg) throws IOException{
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.println(msg);
        pw.flush();
    }
}