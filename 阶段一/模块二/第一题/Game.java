package mk2;

public class Game {
    public int n = 21;// 地图的规模
    public String color;// 确认是白方，还是黑方
    public String mark = "╋";
    public String white = "○";
    public String black = "●";
    public String[][] map = new String[n][n];;
    public String[] coordinate = { "  ","⒈", "⒉", "⒊", "⒋", "⒌", "⒍", "⒎", "⒏", "⒐", "⒑", "⒒", "⒓", "⒔", "⒕", "⒖", "⒗", "⒘",
            "⒙", "⒚", "⒛" };

    public Game() {
        // 初始化地图
        init();
    }

    // 初始化地图
    public void init() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    map[i][j] = coordinate[j];
                } else if (j == 0) {
                    map[i][j] = coordinate[i];
                } else {
                    map[i][j] = mark;
                }
            }
        }
        printMap();
    }

    // 打印地图
    public void printMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    // 解析坐标
    public Point analysisPoint(String point) {
        String[] points = point.split(",");
        int x = Integer.parseInt(points[0]);
        int y = Integer.parseInt(points[1]);
        return new Point(x, y);
    }

    // 落子
    public boolean luoZi(Point point, Boolean color) {
        // 判断是否越界
        if (point.getX() <= 0 || point.getY() > 20 || point.getX() <= 0 || point.getY() > 20) {
            return false;
        }
        // 判断落子的地方有没有其他的子
        if (map[point.getX()][point.getY()] != mark) {
            return false;
        }
        map[point.getX()][point.getY()] = color ? white : black;
        return true;
    }

    // 判断是否输赢
    public boolean isWin(Point point, boolean color) {
        // 纵向
        int zxS = 0;// 纵向上
        for (int i = 0; i < 5; i++) {
            if (point.getX() - i < 0) {
                break;
            }
            if (map[point.getX() - i][point.getY()].equals(color ? white : black)) {
                zxS++;
            } else {
                break;
            }
        }
        int zxX = 0;// 纵向下
        for (int i = 1; i < 5; i++) {
            if (point.getX() + i > 18) {
                break;
            }
            if (map[point.getY() + i][point.getY()].equals(color ? white : black)) {
                zxX++;
            } else {
                break;
            }
        }
        // 横向
        int hxZ = 0;// 横向左
        for (int i = 0; i < 5; i++) {
            if (point.getY() - i < 0) {
                break;
            }
            if (map[point.getX()][point.getY() - i].equals(color ? white : black)) {
                hxZ++;
            } else {
                break;
            }
        }
        int hxY = 0;// 横向右
        for (int i = 1; i < 5; i++) {
            if (point.getY() + i > 18) {
                break;
            }
            if (map[point.getX()][point.getY() + i].equals(color ? white : black)) {
                hxY++;
            } else {
                break;
            }
        }
        // 正斜
        int zxxS = 0;// 正斜上
        for (int i = 0; i < 5; i++) {
            if (point.getY() + i > 18 || point.getX() - i < 0) {
                break;
            }
            if (map[point.getX() - i][point.getY() + i].equals(color ? white : black)) {
                zxxS++;
            } else {
                break;
            }
        }
        int zxxX = 0;// 正斜下
        for (int i = 1; i < 5; i++) {
            if (point.getY() - i < 0 || point.getX() + i > 18) {
                break;
            }
            if (map[point.getX() + i][point.getY() - i].equals(color ? white : black)) {
                zxxX++;
            } else {
                break;
            }
        }
        // 反斜
        int fxxS = 0;// 反斜上
        for (int i = 0; i < 5; i++) {
            if (point.getY() - i < 0 || point.getX() - i < 0) {
                break;
            }
            if (map[point.getX() - i][point.getY() - i].equals(color ? white : black)) {
                fxxS++;
            } else {
                break;
            }
        }
        int fxxX = 0;// 反斜下
        for (int i = 1; i < 5; i++) {
            if (point.getY() + i > 18 || point.getX() + i >18) {
                break;
            }
            if (map[point.getX() + i][point.getY() + i].equals(color ? white : black)) {
                fxxX++;
            } else {
                break;
            }
        }
        System.out.println();
        System.out.print("反斜上↖:" + fxxS+"\t");
        System.out.print("纵向上↑:" + zxS+"\t");
        System.out.print("正斜上↗:" + zxxS);
        System.out.println();
        System.out.print("横向左←:" + hxZ+"\t\t\t");
        System.out.print("横向右→:" + hxY);
        System.out.println();
        System.out.print("正斜下↙:" + zxxX+"\t");
        System.out.print("纵向下↓:" + zxX+"\t");
        System.out.print("反斜下↘:" + fxxX);
        System.out.println();
        if (zxS + zxX > 4 || hxY + hxZ > 4 || zxxS + zxxX > 4 || fxxS + fxxX > 4) {
            return true;
        }
        return false;
    }
}
