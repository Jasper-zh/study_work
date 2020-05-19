import java.util.Arrays;

/**
 * 自定义数组类
 */
public class lg1_4 {

    private static final int init_size = 8; 
    private Object [] objectArray = new Object[8];
    private int size = 8;

    public static void main(String[] args){
        lg1_4 test = new lg1_4();
        for(int i = 0;i<30;i++){
            Object[] a = test.add(i);
            System.out.println(Arrays.toString(a));
            System.out.println("实际数量:"+judgeNumber(a)+",容量大小:"+a.length);
        }

	}
    public static int judgeNumber(Object[] objectArray){
        int n = 0;
        for(int i = 0; i < objectArray.length; i++)
        {
            if(null != objectArray[i]) n++;
        }
        return n;
    }
    public Object[] add(Object o){
		int n = judgeNumber(objectArray);
        objectArray[n]=o;
        if(judgeNumber(objectArray)>=size*0.8){
            size+=size/2;
		    Object[] tu = objectArray;
            objectArray = tu;
		    objectArray = new Object[size];
		    for(int i =0; i< tu.length; i++ ){
		        objectArray[i] = tu[i];
            }
        }
		return objectArray;
    }

}