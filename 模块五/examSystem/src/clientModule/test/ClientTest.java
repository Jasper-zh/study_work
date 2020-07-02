package clientModule.test;

import clientModule.client.ClientMain;
import clientModule.client.ClientScanner;
import clientModule.client.ClientView;


import java.io.IOException;

public class ClientTest {

    public static void main(String[] args) {
        ClientMain clientMain = null;
        try {
            // 1.声明ClientInitClose类型的引用指向该类型的对象
            clientMain = new ClientMain();
            // 2.调用成员方法实现客户端的初始化操作
            clientMain.clientInit();
            // 3.声明ClientView类型的引用指向该类型的对象
            ClientView clientView = new ClientView(clientMain);
            clientView.clientMainPage();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 3.调用成员方法实现客户端的关闭操作
            try {
                clientMain.clientClose();
                ClientScanner.closeScanner();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
