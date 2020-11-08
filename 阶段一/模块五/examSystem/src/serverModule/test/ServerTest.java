package serverModule.test;

import serverModule.server.ServerMain;

import java.io.IOException;

public class ServerTest {
    public static void main(String[] args) {
        ServerMain serverMain = new ServerMain();
            try {
                //初始化服务端和某一客户端链接
                serverMain.init();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    serverMain.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
