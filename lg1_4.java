/**
 * 自定义数组类
 */
public class lg1_4 {

    private static final int init_size = 8; //设定初始大小
    private Object [] objectArray;  //存放值的数组
    private int size;

    /**
     * 构造方法
      */
    public lg1_4(){
        objectArray = new Object [init_size];
    }
    lg1_4(int size){
        if (size <= 0){
            throw new RuntimeException("数组大小不能小于等于0");
        }
        if (size <= 8){
            size = init_size;
        }
        objectArray = new Object [size];
    }

    /**
     * 判断实际元素个数
     */
    public static int judgeNumber(Object[] objectArray){
        int n = 0;  //保存元素个数的变量
        for(int i = 0; i < objectArray.length; i++)
        {
            if(null != objectArray[i]) n++;
        }
        return n;
    }

    public  void add(Object o){
        objectArray[judgeNumber(objectArray)]=o;
        if(judgeNumber(objectArray)>=size*0.8){
            size+=size/2;
        }
    }

}
