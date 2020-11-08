import java.time.Month;
import  java.util.Scanner;
public class lg1_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年份：");
        int year = scanner.nextInt();
        System.out.println("请输入月份：");
        int month = scanner.nextInt();
        System.out.println("请输入日号：");
        int day = scanner.nextInt();

        System.out.println("结果是今年的第："+getYearDay(year,month,day)+"天");

    }

    /**
     * 判断是否为闰年
     * @param year
     * @return
     */
    public static boolean judgeYear(int year){
        if(year%100==0){
            if(year%400==0){
                return true;
            }else{
                return false;
            }
        }else {
            if(year%4==0){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * 得到月天数
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDay(int year,int month){
        boolean flag = judgeYear(year);
        if(month==2){
            if(flag==true){
                return 29;
            }else{
                return 28;
            }
        }else if(month==4||month==6||month==9||month==11){
            return 30;
        }else{
            return 31;
        }
    }

    public static int getYearDay(int year,int month,int day){
        int num = 0;
        for(int i = 1;i<month;i++){
            num += getMonthDay(year,i);
        }
        return num+day;
    }
}
