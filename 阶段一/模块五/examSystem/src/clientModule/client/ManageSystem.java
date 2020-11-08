package clientModule.client;

import clientModule.domain.Subject;
import clientModule.domain.User;
import clientModule.domain.UserMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageSystem {
    private Scanner scanner = ClientScanner.getScanner();
    private ClientMain clientMain;
    private ClientView clientView;


    public ManageSystem(ClientMain clientMain) {
        this.clientMain = clientMain;
        this.clientView = new ClientView(clientMain);
    }

    public void managePage() throws Exception {
        while (true) {
            System.out.println("  \t\t\t\t\t\t   管理员模块");
            System.out.println("--------------------------------------------------------------------");
            System.out.print("   [1] 添加学员");
            System.out.print("   [2] 修改学员");
            System.out.print("   [3] 删除学员");
            System.out.println("   [4] 查询学员");
            System.out.print("   [5] 增加考題");
            System.out.print("   [6] 修改考題");
            System.out.print("   [7] 刪除考題");
            System.out.println("   [8] 查詢考題");
            System.out.print("   [9] 導入考題");
            System.out.println("   [10] 退到登陆页");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("请选择要进行的业务编号：");
            //Scanner sc = new Scanner(System.in);
            //int choose = sc.nextInt();
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("增加学员");
                    changeStu("addStu");
                    break;
                case 2:
                    System.out.println("修改学员");
                    changeStu("editStu");
                    break;
                case 3:
                    System.out.println("删除学员");
                    deleteStu();
                    break;
                case 4:
                    System.out.println("查询学员");
                    selectStu();
                    break;
                case 5:
                    System.out.println("增加考题");
                    changeSub("addSub");
                    break;
                case 6:
                    System.out.println("修改考题");
                    changeSub("editSub");
                    break;
                case 7:
                    System.out.println("删除考题");
                    deleteSub();
                    break;
                case 8:
                    System.out.println("查询考题");
                    selectSub();
                    break;
                case 9:
                    System.out.println("导入考题");
                    break;
                case 10:
                    System.out.println("退出登陆");
                    try {
                        exit();
                        clientView.clientMainPage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }
    public void exit() throws Exception {
        //让服务端保存数据到持久层
        clientMain.getOos().writeObject(new UserMessage("save"));
        //读取反馈
        System.out.println(clientMain.getOis().readObject());
    }

    public void changeStu(String str) throws Exception {
        //输入学生信息（目前仅用户名密码）
        System.out.println("请输入用户名：");
        String username = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();
        //接收完页面数据后提交给服务端进行添加
        User user = new User(username,password);
        //告知服务器操作类型以及数据
        clientMain.getOos().writeObject(new UserMessage(str,user));
        //读取反馈
        System.out.println(clientMain.getOis().readObject());
    }
    public void deleteStu() throws Exception{
        //输入学生信息（目前仅用户名密码）
        System.out.println("请输入用户名：");
        String username = scanner.next();
        //接收完页面数据后提交给服务端进行添加
        User user = new User(username,null);
        //告知服务器操作类型以及数据
        clientMain.getOos().writeObject(new UserMessage("deleteStu",user));
        //读取反馈
        System.out.println(clientMain.getOis().readObject());
    }
    public void selectStu() throws Exception{
        //发送查询请求
        clientMain.getOos().writeObject(new UserMessage("selectStu"));
        //读取反馈信息
        List<User> userList = (List<User>) clientMain.getOis().readObject();
        System.out.println(userList.toString());
    }
    public void changeSub(String str) throws IOException, ClassNotFoundException {
        System.out.println("输入题目：");
        String problem = scanner.next();
        System.out.println("输入选项：");
        StringBuilder selects = new StringBuilder();
        selects.append("A."+scanner.next()+"、").append("B."+scanner.next()+"、").append("C."+scanner.next()+"、").append("D."+scanner.next());
        System.out.println("输入答案：");
        String answer = scanner.next();
        //封装
        Subject subject = new Subject(problem,selects.toString(),answer);
        //发送请求
        clientMain.getOos().writeObject(new UserMessage(str,subject));
        //接收反馈
        System.out.println(clientMain.getOis().readObject());
    }
    public void deleteSub() throws Exception{
        System.out.println("请输入题目：");
        String problem = scanner.next();
        //接收完页面数据后提交给服务端进行添加
        Subject subject = new Subject(problem, null, null);
        //告知服务器操作类型以及数据
        clientMain.getOos().writeObject(new UserMessage("deleteSub",subject));
        //读取反馈
        System.out.println(clientMain.getOis().readObject());
    }
    public void selectSub() throws Exception{
        //发送查询请求
        clientMain.getOos().writeObject(new UserMessage("selectSub"));
        //读取反馈信息
        List<Subject> subjectList = (List<Subject>) clientMain.getOis().readObject();
        System.out.println(subjectList.toString());
    }
    public void importSub() throws IOException, ClassNotFoundException {
        clientMain.getOos().writeObject(new UserMessage("importSub"));
        System.out.println(clientMain.getOis().readObject());
    }

}
