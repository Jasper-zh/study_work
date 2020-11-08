package mk3.one;

public class FilterChar {
    char[] upper = new char[100];
    char[] lower = new char[100];
    char[] number = new char[100];
    char[] other = new char[100];

    public static void main(String[] args) {
        String str = "ABCD123!@#$%ab";
        FilterChar filterChar = new FilterChar();
        filterChar.solve(str);
        System.out.println(filterChar.upper);
        System.out.println(filterChar.lower);
        System.out.println(filterChar.number);
        System.out.println(filterChar.other);
    }
    public void solve(String str){
        int ui = 0;
        int li = 0;
        int ni = 0;
        int oi = 0;
        for(int i=0; i<str.length();i++){
            char c = str.charAt(i);
            if(c>='0'&&c<='9'){
                number[ni] = c;
                ni++;
            }else if(c>='a'&&c<='z'){
                lower[li] = c;
                li++;
            }else if(c>='A'&&c<='Z'){
                upper[ui] = c;
                ui++;
            }else{
                other[oi] = c;
                oi++;
            }
        }
    }

}
