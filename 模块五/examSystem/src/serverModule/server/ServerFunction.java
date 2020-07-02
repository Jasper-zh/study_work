package serverModule.server;
import clientModule.domain.Grade;
import clientModule.domain.Subject;
import clientModule.domain.User;
import clientModule.domain.UserMessage;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务功能部分
 */
public class ServerFunction extends Thread{
    //使用合成复用原则
    private ServerMain serverMain;
    //注入dao在业务层与持久层做数据交互
    private ServerDao serverDao = new ServerDao();
    //当前用户
    private String username;
    //当前成绩
    private Integer grade;
    //反馈信息
    private String str;

    /**
     * 传入serverMain继续和客户端沟通
     * @param serverMain
     */
    public ServerFunction(ServerMain serverMain) {
        this.serverMain = serverMain;
    }

    /**
     * 自定义成员方法实现客户端发来消息的接收并处理
     */
    public void run() {

        while (true){
            //读取操作请求
            System.out.println("读取操作请求");
            ObjectInputStream objectInputStream = serverMain.getObjectInputStream();
            UserMessage msg = null;
            try {
                msg = (UserMessage)objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String type = msg.getType();
            System.out.println("来自客户端的请求是："+type);
            //-------------------登陆-------------------
            if("学员".equals(type)){
                username = msg.getUser().getUserName();
                try {
                    if (serverDao.serverStudentCheck(msg.getUser())) {
                        msg.setType("success");
                    } else {
                        msg.setType("fail");
                    }
                    //反馈登陆信息
                    serverMain.getObjectOutputStream().writeObject(msg);
                    //如果登陆失败继续
                    if("fail".equals(msg.getType())){
                        continue;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("管理员".equals(type)){
                if (serverDao.serverManagerCheck(msg.getUser())) {
                    msg.setType("success");
                } else {
                    msg.setType("fail");
                }
                try {
                    serverMain.getObjectOutputStream().writeObject(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if("fail".equals(msg.getType())){
                    continue;
                }
            }
            //----------------------学员管理----------------------
            if("addStu".equals(type)){
                //通过dao层完成数据操作
                serverDao.addUser(msg.getUser());
                System.out.println("添加完成");
                try {
                    serverMain.getObjectOutputStream().writeObject("添加成功");
                    serverMain.getObjectOutputStream().reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("editStu".equals(type)){
                int i = serverDao.editUser(msg.getUser());
                if(i==0){
                    str="不存在请先添加";
                }else{
                    str="已更新";
                }
                try {
                    serverMain.getObjectOutputStream().writeObject(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("deleteStu".equals(type)){
                int i = serverDao.deleteUser(msg.getUser().getUserName());
                if(i==0){
                    str="不存在请先添加";
                }else{
                    str="已删除";
                }
                try {
                    serverMain.getObjectOutputStream().writeObject(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("selectStu".equals(type)){
                List<User> userList=null;
                userList = serverDao.selectAllUser();
                try {
                    System.out.println("server:"+userList.toString());
                    serverMain.getObjectOutputStream().writeObject(userList);
                    serverMain.getObjectOutputStream().reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //----------------------------考题管理-------------------------------
            if("addSub".equals(type)){
                //通过dao层完成数据操作
                serverDao.addSubject(msg.getSubject());
                System.out.println("添加完成");
                try {
                    serverMain.getObjectOutputStream().writeObject("添加成功");
                    serverMain.getObjectOutputStream().reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("editSub".equals(type)){
                int i = serverDao.editSubject(msg.getSubject());
                if(i==0){
                    str="不存在请先添加";
                }else{
                    str="已更新";
                }
                try {
                    serverMain.getObjectOutputStream().writeObject(str);
                    serverMain.getObjectOutputStream().reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("deleteSub".equals(type)){
                int i = serverDao.deleteSub(msg.getSubject().getProblem());
                if(i==0){
                    str="不存在请先添加";
                }else{
                    str="已删除";
                }
                try {
                    serverMain.getObjectOutputStream().writeObject(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("selectSub".equals(type)){
                List<Subject> subjectList=null;
                subjectList = serverDao.selectAllSubject();
                try {
                    System.out.println("server:"+subjectList.toString());
                    serverMain.getObjectOutputStream().writeObject(subjectList);
                    serverMain.getObjectOutputStream().reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("importSub".equals(type)){
                try {
                    serverDao.importAllSub();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("save".equals(type)){
                try {
                    serverDao.saveStu();
                    serverDao.saveSub();
                    serverDao.saveGrade();
                    //清空缓存
                    username = null;
                    grade = null;
                    serverMain.getObjectOutputStream().writeObject("自动保存");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //----------------------------考试----------------------------------
            if("startExam".equals(type)){
                List<Subject> subjectList=null;
                subjectList = serverDao.selectAllSubject();
                try {
                    System.out.println("server:"+subjectList.toString());
                    serverMain.getObjectOutputStream().writeObject(subjectList);
                    serverMain.getObjectOutputStream().reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String answers = null;
                try {
                    //接收答案
                    answers = (String)serverMain.getObjectInputStream().readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                int grade = 0;
                for (int i=0;i<subjectList.size();i++) {
                    System.out.println(answers.charAt(i));
                    if(subjectList.get(i).getAnswer().equals(String.valueOf(answers.charAt(i)))){
                        grade++;
                    }
                }
                this.grade = grade;
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String format = dateFormat.format(date);
                serverDao.addGrade(new Grade(username,String.valueOf(grade),format));
            }
            if("selectGrade".equals(type)){
                try {
                    if(grade!=null){
                        serverMain.getObjectOutputStream().writeObject("本次考试成绩为："+grade);
                    }else {
                        serverMain.getObjectOutputStream().writeObject("请先参加考试！");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("selectAllGrade".equals(type)){
                List<Grade> gradeList=null;
                gradeList = serverDao.selectAllGrade();
                try {
                    System.out.println("server:"+gradeList.toString());
                    serverMain.getObjectOutputStream().writeObject(gradeList);
                    serverMain.getObjectOutputStream().reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if("resetPassword".equals(type)){
                serverDao.editUser(new User(username,msg.getUser().getPassword()));
            }

            System.out.println("请求处理完毕等待下次请求");
        }

    }
}
