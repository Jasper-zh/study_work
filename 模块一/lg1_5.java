public class lg1_5 {
    public static void main(String[] args) {
        chessboard();
    }
    public static void chessboard(){

       for(int i=0;i<16;i++){
           for (int j=0;j<16;j++){
               if(i==0&&j==0){
                   System.out.print("   ");
               }
               else if(i==0){
                   System.out.print(Integer.toHexString(j)+"  ");
               }else if(j==0) {
                   System.out.print(Integer.toHexString(i) + "  ");
               }else {
                   System.out.print("+"+"  ");
               }
           }
           System.out.println();
       }
    }
}
