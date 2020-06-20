package mk4.three;

import java.io.*;
import java.util.concurrent.Callable;

public class ThreadCallableTest implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("拷贝线程开启");
        File srcFile=new File("F:/idea/homework/src/mk4/three/srcFile");
        File targetFile=new File("F:/idea/homework/src/mk4/three/targetFile");
        copyDir(srcFile,targetFile);
        System.out.println("完成拷贝！");
        return null;
    }
    public static void copyDir(File srcDir,File targetDir){
//先判断目标目录是否为空，否则添加该目录
        if(!targetDir.exists()){
            targetDir.mkdir();
        }
        File[] files=srcDir.listFiles();//获取指定目录下的所有文件
        for(File f:files){
            if(f.isFile()){
                copyFile(new File(srcDir+"/"+f.getName()), new File(targetDir+"/"+f.getName()));
            }else{
                //调用该方法使用递归实现
                copyDir(new File(srcDir+"/"+f.getName()), new File(targetDir+"/"+f.getName()));
            }
        }
    }
    public static void copyFile(File srcFile, File targetFile) {
        // 增加缓冲流
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcFile));
            bos = new BufferedOutputStream(new FileOutputStream(targetFile));
            byte[] burf = new byte[1024];// 定义中转站
            int len = 0;//获取到字节的长度
            while ((len = bis.read(burf)) != -1) {
                bos.write(burf, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        try {
            if (bos != null) {
                bos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (bos != null) {
                bis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
