package mk4.three;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyFile {
    public static void main(String[] args) {
        //1.创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //2.发布任务
        executorService.submit(new ThreadCallableTest());
        //3.关闭线程池
        executorService.shutdown();
    }
}
