package mk2;

import java.util.Scanner;

public class Start {
    public static String white = "白色";
    public static String black = "黑色";
    public static boolean color=true;
    public static String spoint;//存储坐标
    public static void main(String[] args) {

        Game game = new Game();
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("请"+(color?white:black)+"落子：");
            spoint=scanner.next();//获得坐标
            Point point=game.analysisPoint(spoint);//解析坐标，并返回坐标对象

            if(game.luoZi(point,color)){
                game.printMap();
                if(game.isWin(point,color)){
                    System.out.println(""+(color?white:black)+"赢了！");
                    break;
                }
                color=!color;
            }
        }

    }
}
