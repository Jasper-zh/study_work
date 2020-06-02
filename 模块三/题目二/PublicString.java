package mk3.two;

public class PublicString {
    public static void main(String[] args) {
        String s1="asdafghjka",s2="aaasdfg";
        System.out.println(getCommonStrLength(s1,s2));
    }

    private static int getCommonStrLength(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                dp[i][j] = 0;
            }
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        int max = 0;
        int[] index = new int[2];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        for (int i = 0;i<max;i++){
            System.out.print(str1.charAt(index[0]-i));
        }
        System.out.println();
        return max;
    }

}
