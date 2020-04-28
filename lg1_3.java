import java.util.Arrays;
import java.util.Random;

public class lg1_3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getRandomArr()));
    }
    public static int[] getRandomArr(){
        int[] arr = new int[7];
        Random random = new Random();
        int flag = 1;
        for(int i=0; i<6;i++){
            int a = random.nextInt(33) + 1;
            for(int j=0;j<i;j++){
                if(arr[j]==a){
                    flag = 0;
                }
            }
            if(flag==1){
                arr[i] = a;
            }else {
                i--;
                flag=1;
            }
        }
        arr[6] = random.nextInt(16)+1;
        return arr;
    }
}
