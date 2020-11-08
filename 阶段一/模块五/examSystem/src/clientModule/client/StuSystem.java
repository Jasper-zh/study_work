package clientModule.client;

import clientModule.domain.Grade;
import clientModule.domain.Subject;
import clientModule.domain.User;
import clientModule.domain.UserMessage;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StuSystem {
    ClientMain clientMain;
    List<String> users = new ArrayList<>();
    ClientView clientView;
    Scanner scanner = ClientScanner.getScanner();

    public StuSystem(ClientMain clientMain) {
        this.clientMain = clientMain;
        clientView = new ClientView(clientMain);

    }


    public void stuPage() throws Exception {
        while (true) {
            System.out.println("  \t\t\t\t\t\t   学员模块");
            System.out.println("--------------------------------------------------------------------");
            System.out.print("   [1] 开始考试");
            System.out.print("   [2] 导出成绩");
            System.out.print("   [3] 查询成绩");
            System.out.println("   [4] 修改密码");
            System.out.println("   [5] 退到登陆页");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("请选择要进行的业务编号：");
            //Scanner sc = new Scanner(System.in);
            //int choose = sc.nextInt();
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("开始考试");
                    start();
                    break;
                case 2:
                    System.out.println("导出成绩");
                    exportGrade();
                    break;
                case 3:
                    System.out.println("查询成绩");
                    selectGrade();
                    break;
                case 4:
                    System.out.println("修改密码");
                    resetPassword();
                    break;
                case 5:
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
    public void start() throws IOException, ClassNotFoundException {
        //发送考试请求
        clientMain.getOos().writeObject(new UserMessage("startExam"));
        //得到试卷反馈
        List<Subject> subjectList = (List<Subject>) clientMain.getOis().readObject();
        for (int i=1; i<=subjectList.size();i++) {
            Subject subject = subjectList.get(i-1);
            System.out.println("（"+i+"）"+subject.getProblem());
            String[] split = subject.getSelect().split("、");
            System.out.println(split[0]);
            System.out.println(split[1]);
            System.out.println(split[2]);
            System.out.println(split[3]);
        }
        System.out.println("填写你的答案：");
        String next = scanner.next();
        //提交答案
        clientMain.getOos().writeObject(next);

    }
    public void exportGrade() throws IOException, ClassNotFoundException {
        //发送查询请求
        clientMain.getOos().writeObject(new UserMessage("selectAllGrade"));
        //读取反馈信息
        List<Grade> gradeList = (List<Grade>) clientMain.getOis().readObject();
        System.out.println(gradeList.toString());
    }
    public void selectGrade() throws IOException, ClassNotFoundException {
        //发送查询请求
        clientMain.getOos().writeObject(new UserMessage("selectGrade"));
        String i = (String) clientMain.getOis().readObject();
        System.out.println(i);
    }
    public void resetPassword() throws IOException, ClassNotFoundException {
        //发送查询请求
        clientMain.getOos().writeObject(new UserMessage("resetPassword",new User(null,scanner.next())));

    }

    public void exit() throws Exception {
        //让服务端保存数据到持久层
        clientMain.getOos().writeObject(new UserMessage("save"));
        //读取反馈
        System.out.println(clientMain.getOis().readObject());
    }
}
