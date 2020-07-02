package serverModule.server;


import clientModule.domain.Grade;
import clientModule.domain.Subject;
import clientModule.domain.User;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 编程实现数据的存取
 */
public class ServerDao {
    private List<User> userList;
    private List<Subject> subjectList;
    private List<Grade> gradeList;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    /**
     * 读取数据
     */
    {
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //在一开始读取信息
    public ServerDao(){
        super();
        try {
            userList = importAllUser();
            subjectList = importAllSub();
            gradeList = importAllGrade();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 编程实现管理员账号和密码的校验并将结果返回出去
     * @param user
     * @return
     */
    public boolean serverManagerCheck(User user) {
        if ("admin".equals(user.getUserName()) && "123456".equals(user.getPassword())) {
            return true;
        }
        return false;
    }
    /**
     * 从数据库校验用户名密码
     * @param user
     * @return
     * @throws IOException
     */
    public boolean serverStudentCheck(User user) throws IOException {
        //获取全部数据
        List<User> userList = selectAllUser();
        //再进行一一校验
        for (User user1 : userList) {
            if(user.equals(user1)){
                return true;
            }
        }
        return false;
    }
    /**
     * 从数据库读取全部User
     * @return
     */
    public List<User> importAllUser() throws IOException {
        bufferedReader = new BufferedReader(new FileReader("F:/idea/examSystem/src/serverModule/database/student.txt"));
        List<User> userList = new ArrayList<>();
        String s = null;
        while ((s=bufferedReader.readLine())!=null && s.length()!=0){
            String[] split = s.split(",");
            User user0 = new User(split[0], split[1]);
            userList.add(user0);
        }
        System.out.println("读取到数据");
        System.out.println("dao-import:"+userList.toString());
        return  userList;
    }
    public List<Subject> importAllSub() throws IOException {
        bufferedReader = new BufferedReader(new FileReader("F:/idea/examSystem/src/serverModule/database/subject.txt"));
        List<Subject> subjectList = new ArrayList<>();
        String s = null;
        while ((s=bufferedReader.readLine())!=null && s.length()!=0){
            String[] split = s.split(",");
            Subject subject = new Subject(split[0], split[1], split[2].trim());
            subjectList.add(subject);
        }
        System.out.println("dao-importSubject:"+subjectList.toString());
        return  subjectList;
    }
    public List<Grade> importAllGrade() throws IOException {
        bufferedReader = new BufferedReader(new FileReader("F:/idea/examSystem/src/serverModule/database/grade.txt"));
        List<Grade> gradeList = new ArrayList<>();
        String s = null;
        while ((s=bufferedReader.readLine())!=null && s.length()!=0){
            String[] split = s.split(",");
            Grade grade = new Grade(split[0],split[1],split[2]);
            gradeList.add(grade);
        }
        System.out.println("dao-importGrade:"+gradeList.toString());
        return  gradeList;
    }

    //---------------------------学员管理----------------------------
    public List<User> selectAllUser(){
        System.out.println("dao-get:"+userList.toString());
        return userList;
    }
    public void addUser(User user) {
        userList.add(user);
        System.out.println("dao-add:"+userList.toString());
    }
    public int editUser(User user) {
        int flag = 0;
        for (User user1 : userList) {
            if(user.getUserName().equals(user1.getUserName())){
                user1.setPassword(user.getPassword());
                flag = 1;
            }
        }
        System.out.println(userList.toString());
        return flag;
    }
    public int deleteUser(String userName){
        for (User user : userList) {
            if(user.getUserName().equals(userName)){
                userList.remove(user);
                return 1;
            }
        }
        return 0;
    }
    //----------------------------考题管理---------------------------
    public void addSubject(Subject subject){
        subjectList.add(subject);
        System.out.println("dao-addSubject:"+subjectList.toString());
    }
    public List<Subject> selectAllSubject(){
        System.out.println("dao-selectSubject:"+subjectList.toString());
        return subjectList;
    }
    public int editSubject(Subject subject) {
        int flag = 0;
        for (Subject subject1 : subjectList) {
            if(subject.getProblem().equals(subject1.getProblem())){
                subject1.setSelect(subject.getSelect());
                subject1.setAnswer(subject.getAnswer());
                flag = 1;
            }
        }
        System.out.println("dao-editSubject:"+subjectList.toString());
        return flag;
    }
    public int deleteSub(String problem){
        for (Subject subject : subjectList) {
            if(subject.getProblem().equals(problem)){
                subjectList.remove(subject);
                return 1;
            }
        }
        return 0;
    }
    //----------------------------成绩--------------------------------
    public void addGrade(Grade grade) {
        gradeList.add(grade);
        System.out.println("dao-addSubject:"+subjectList.toString());
    }
    public List<Grade> selectAllGrade(){
        System.out.println("dao-selectGrade:"+gradeList.toString());
        return gradeList;
    }
    //----------------------------保存--------------------------------
    public void saveStu() throws IOException {
        bufferedWriter= new BufferedWriter(new FileWriter("F:/idea/examSystem/src/serverModule/database/student.txt"));
        System.out.println("dao-saveStudent:"+userList.toString());
        for (User user : userList) {
            String line = user.getUserName()+","+user.getPassword();
            System.out.println(line);
            bufferedWriter.write(line+"\t\n");
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }
    public void saveSub() throws IOException {
        bufferedWriter= new BufferedWriter(new FileWriter("F:/idea/examSystem/src/serverModule/database/subject.txt"));
        System.out.println("dao-saveSubject:"+subjectList.toString());
        for (Subject subject : subjectList) {
            String line = subject.getProblem()+","+subject.getSelect()+","+subject.getAnswer();
            System.out.println(line);
            bufferedWriter.write(line+"\t\n");
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }
    public void saveGrade() throws IOException {
        bufferedWriter= new BufferedWriter(new FileWriter("F:/idea/examSystem/src/serverModule/database/grade.txt"));
        System.out.println("dao-saveGrade:"+subjectList.toString());
        for (Grade grade : gradeList) {
            String line = grade.getUsername()+","+grade.getGrade();
            System.out.println(line);
            bufferedWriter.write(line+"\t\n");
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }

}
