import java.util.Arrays;

public class lg1_2 {
    public static void main(String[] args) {
        getWS(1000);
    }

    public static void getWS(int number){
        int num = 0;
        for(int i =1; i<=number; i++){
            for(int j=1;j<=i/2;j++){
                if(i%j==0){
                    num+=j;
                }
            }
            if(num == i){
                System.out.println(i);
            }
            num=0;
        }
    }

}
