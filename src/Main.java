import java.util.Scanner;

/**
 * @author ${USER}
 * @date ${DATE} ${TIME}
 */
public class Main {
    //时间限制： 3000MS
    //内存限制： 589824KB
    //题目描述：
    //小美有一个由数字字符组成的字符串。现在她想对这个字符串进行一些修改。具体地，她可以将这个字符串中任意位置字符修改为任意的数字字符。她想知道，至少进行多少次修改，可以使得修改后的字符串不包含两个连续相同的字符？
    //
    //例如，对于字符串”111222333”，她可以进行3次修改将其变为”121212313”。
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String str=cin.next();
        char pre=' ';
        int res=0;
        char[] cs=str.toCharArray();
        //Arrays.sort(cs);
        for(char c:cs){
            if(pre==c){
                res++;
                pre=' ';
                continue;
            }
            pre=c;
        }
        System.out.println(res);
    }
}