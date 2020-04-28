public class lg1_5 {
    public static void main(String[] args) {
        chessboard();
    }
    public static void chessboard(){

       for(int i=0;i<17;i++){
           for (int j=0;j<16;j++){
               System.out.print("+"+"  ");
           }
           System.out.println();
       }
    }
}
