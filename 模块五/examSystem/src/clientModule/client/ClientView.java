package clientModule.client;

import clientModule.domain.User;
import clientModule.domain.UserMessage;
import serverModule.server.ServerMain;

import java.io.IOException;

/**
 * 编程实现客户端的主界面绘制和相应功能的实现
 */
public class ClientView {
    /**
     * 为了可以使用输入输出流，采用合成复用原则实现
     */
    private  ClientMain clientMain;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param clientMain
     */
    public ClientView(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    /**
     * 自定义成员方法实现客户端主界面的绘制
     */
    public void clientMainPage() throws Exception {
        while(true) {
            System.out.println("  \t\t\t   在线考试系统");
            System.out.println("-------------------------------------------");
            System.out.print("   [1] 学员系统");
            System.out.println("     [2] 管理员系统");
            System.out.println("   [0] 退出系统");
            System.out.println("-------------------------------------------");
            System.out.println("请选择要进行的业务编号：");
            //Scanner sc = new Scanner(System.in);
            //int choose = sc.nextInt();
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    login("学员");
                    break;
                case 2:
                    login("管理员");
                    break;
                case 0:
                    System.out.println("正在退出系统...");
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    /**
     * 自定义成员方法实现登录的功能
     *
     * 根据参数判断是学员登陆还是管理员登陆
     */
    private void login(String str) throws Exception {
            // 1.准备管理员登录的相关数据
            System.out.println("请输入" + str + "的账户信息：");
            String userName = ClientScanner.getScanner().next();
            System.out.println("请输入" + str + "的密码信息：");
            String password = ClientScanner.getScanner().next();
            UserMessage userMessage = new UserMessage(str, new User(userName, password));
            // 2.将UserMessage类型的对象通过对象输出流发送给服务器
            clientMain.getOos().writeObject(userMessage);
            System.out.println("客户端发送" + str + "账户信息成功！");
            // 3.接收服务器的处理结果并给出提示
            userMessage = (UserMessage) clientMain.getOis().readObject();
            if ("success".equals(userMessage.getType())) {
                System.out.println("登录成功，欢迎使用！");
                //在进系统之前导入数据到程序
                //进对应系统并携带当前clienMian
                if("管理员".equals(str)) {
                    ManageSystem manageSystem = new ManageSystem(clientMain);
                    manageSystem.managePage();
                }
                if("学员".equals(str)){
                    StuSystem stuSystem = new StuSystem(clientMain);
                    stuSystem.stuPage();
                }
            } else {
                System.out.println("用户名或密码错误！");
                clientMainPage();
            }
    }
}
