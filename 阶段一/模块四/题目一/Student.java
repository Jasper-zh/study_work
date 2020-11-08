package mk4.one;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    Scanner sc = new Scanner(System.in);
    public static ArrayList<Student> st = new ArrayList<Student>();
    private String name;
    private int age;
    private String ID;

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setID(String ID) { this.ID = ID; }
    public void setName() {
        //输入学生姓名赋值
        System.out.println("请输入学生的姓名:");
        this.name = sc.nextLine();
    }
    public void setAge() throws Exception{
        //输入学生年龄赋值
        System.out.println("请输入学生的年龄:");
        int age = sc.nextInt();
        if(age>0&&age<150){
            this.age = age;
        }else {
            throw new AgeException("年龄不合法");
        }
    }
    public void setID() throws Exception{
        //输入学生学号赋值
        System.out.println("请输入学生的学号:");
        String id = sc.nextLine();
        if(id.matches("[abc][0-9]{3,5}")){
            this.ID = id;
        }else {
            throw new SnoException("学号不合法");
        }
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "学生信息 [姓名=" + name + ", 年龄=" + age + ", 学号=" + ID + "]" + "\n";
    }

    // 欢迎模块
    public static void welcome() {
        System.out.println("学生信息管理系统");
        System.out.println("****欢迎您****");
    }

    //成绩汇总模块
    public static void look(ArrayList<Student> li) {
        System.out.println("学生信息汇总表:");
        System.out.println(li);
    }

    //登陆模块
    public static boolean Login() {
        welcome();
        String name1 = "root";
        String password = "123456";
        for (int i = 3; i > 0; i--) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入管理员用户名:");
            String user = sc.nextLine();
            System.out.println("请输入管理员登陆密码:");
            String Password = sc.nextLine();
            if (name1.equals(user) && password.equals(Password)) {
                System.out.println("用户名和密码格式输入正确，登陆成功");
                return true;
            } else {
                if (i == 1) {
                    System.out.println("用户名和密码连续错误3次，系统自动退出");
                    System.exit(0);
                } else {
                    System.out.println("输入错误，请从新输入，你还有" + (i - 1) + "次机会");
                }
            }
        }
        return false;
    }

    //学生成绩查询模块
    public static void check(ArrayList<Student> li) {
        Scanner sc = new Scanner(System.in);
        if(li.size()==0){
            System.out.println("现在没有任何学生信息，可以去添加！");
        }else {
            System.out.println("请输入学生学号查询学生信息:");
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).getID().equals(sc.nextLine())) {
                    System.out.println(li.get(i));
                } else {
                    System.out.println("学生档案库里面没有该学号，请从新输入！");
                }
            }
        }
    }

    //删除学生信息模块
    public static void delete(ArrayList<Student> li) {
        Scanner sc = new Scanner(System.in);
        if(li.size()==0){
            System.out.println("现在没有任何学生信息，可以去添加！");
        }else{
            System.out.println("请输入学生学号删除学生信息:");
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).getID().equals(sc.nextLine())) {
                    li.remove(i);
                    System.out.println("学生信息删除成功！");
                } else {
                    System.out.println("学生档案库里面没有该学号，请从新输入！");
                }
            }
        }
    }

    //学生信息添加模块
    public static void judge(ArrayList<Student> li) {
        Student stu = null;
        try {
            stu = new Student();
            stu.setID();
            stu.setName();
            stu.setAge();
            if (!li.contains(stu)) {
                li.add(stu);
                System.out.println("学生信息添加成功！");
            } else {
                System.out.println("学生信息输入重复！");
            }
            System.out.println(li);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void add(ArrayList<Student> li) throws Exception {
        System.out.println("-------------------添加学生------------------");
        Student s = new Student();
        s.setID();
        s.setName();
        s.setAge();
        li.add(s);
    }

    //修改学生信息模块
    public static void revise(ArrayList<Student> li) {
        Scanner sc = new Scanner(System.in);
        if(li.size()==0){
            System.out.println("现在没有任何学生信息，可以去添加！");
        }else {
            System.out.println("请输入需要修改信息的这位学生的学号:");
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).getID().equals(sc.nextLine())) {
                    Student st = null;
                    try {
                        st = new Student();
                        st.setID();
                        st.setName();
                        st.setAge();
                        li.set(i, st);
                        System.out.println("修改成功！");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("学生档案库里面没有该学号，请从新输入！");
                }
            }
        }
    }
    //退出系统模块
    public static void exit(ArrayList<Student> li){
        BufferedWriter bufferedWriter = null;
        try {
            //创建缓存字符输出流——输出到file.txt
            bufferedWriter = new BufferedWriter(new FileWriter("F:/idea/homework/src/mk4/one/file.txt"));
            //遍历数据一行一条
            for (int i =0; i<li.size();i++) {
                Student student = li.get(i);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(student.getID()).append(",").append(student.getName()).append(",").append(student.getAge());
                bufferedWriter.write(String.valueOf(stringBuilder));
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    //管理系统菜单模块
    public static void run(ArrayList<Student> li) throws Exception {
        //从本地加载学生信息到集合li
        BufferedReader bufferedReader = null;
        Student student = new Student();
        bufferedReader = new BufferedReader(new FileReader("F:/idea/homework/src/mk4/one/file.txt"));
        String line = null;
        while ((line = bufferedReader.readLine()) !=null && line.length()!=0) {
            String[] split = line.split(",");
            student.setID(split[0]);
            student.setName(split[1]);
            student.setAge(Integer.parseInt(split[2]));
            li.add(student);
        }
        bufferedReader.close();
        Scanner sc = new Scanner(System.in);
        if (Login()) {
            while (true) {
                System.out.println("请按照提示输入数字查询你所需要的功能:");
                System.out.println("1,查看所有学生信息。2,根据学号查找某个学生的信息。3,根据学号删除某个学生的信息。");
                System.out.println("4,添加一个学生信息。5,根据学号修改某个学生的信息。6,退出。");
                switch (sc.nextInt()) {
                    case 1:
                        look(li);
                        break;
                    case 2:
                        check(li);
                        break;
                    case 3:
                        delete(li);
                        break;
                    case 4:
                        judge(li);
                        break;
                    case 5:
                        revise(li);
                        break;
                    case 6:
                        exit(li);
                        break;
                    default:
                        System.out.println("请按照提示输入！");
                        break;
                }
            }
        }
    }

    //主方法
    public static void main(String[] args) {

        try {
            run(st);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
