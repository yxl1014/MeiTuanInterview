import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yxl
 * @date 2023/3/11 下午7:19
 */
public class MM {
    //时间限制： 3000MS
    //内存限制： 589824KB
    //题目描述：
    //小团在一个n*m的网格地图上探索。网格地图上第 i 行第 j 列的格子用坐标(i,j)简记。
    // 初始时，小团的位置在地图的左上角，即坐标(1,1)。地图上的每一个格子上都有一定的金币，特别地，
    // 小团位于的初始位置(1,1)上的金币为0。小团在进行探索移动时，可以选择向右移动一格（即从(x,y)到达(x,y+1)）
    // 或向下移动一格（即从(x,y)到达(x+1,y)）。地图上的每个格子都有一个颜色，红色或蓝色。如果小团一次移动前后的两个格子颜色不同，
    // 那么他需要支付 k 个金币才能够完成这一次移动；如果移动前后的两个格子颜色相同，则不需要支付金币。小团可以在任意格子选择结束探索。
    // 现在给你网格地图上每个格子的颜色与金币数量，假设小团初始时的金币数量为0，请你帮助小团计算出最优规划，使他能获得最多的金币，
    // 输出能获得的最多金币数量即可。
    //
    //注意：要求保证小团任意时刻金币数量不小于零。

    /*第一行是三个用空格隔开的整数n、m和k，表示网格地图的行数为n，列数为m，在不同颜色的两个格子间移动需要支付k个金币。

接下来n行，每行是一个长度为m的字符串，字符串仅包含字符’R’或’B’。第 i 行字符串的第 j 个字符表示地图上第i行第j列的格子颜色，如果字符为’R’则表示格子颜色为红色，为’B’表示格子颜色为蓝色。

接下来是一个n行m列的非负整数矩阵，第 i 行第 j 列的数字表示地图上第 i 行第 j 列的格子上的金币数量。保证所有数据中数字大小都是介于[0, 10]的整数。

1<=n,m<=200, 1<=k<=5。*/
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n, m, k;
        try {
            String[] s = reader.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            m = Integer.parseInt(s[1]);
            k = Integer.parseInt(s[2]);
            char[][] color = new char[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                char[] ss = reader.readLine().toCharArray();
                for (int j = 1; j <= m; j++) {
                    color[i][j] = ss[j - 1];
                }
            }
            int[][] doll = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                String[] ss = reader.readLine().split(" ");
                for (int j = 1; j <= m; j++) {
                    doll[i][j] = Integer.parseInt(ss[j-1]);
                }
            }
            int l = dps(color, doll, k, 1, 2, n, m, 0, color[1][1]);
            int e = dps(color, doll, k, 2, 1, n, m, 0, color[1][1]);
            System.out.println(l > e ? l : e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int dps(char[][] color, int[][] doll, int k, int i, int j, int n, int m, int now, char c) {
        if (i > n || j > m) {
            return now;
        }
        int t = now;
        int d = doll[i][j];
        char cc = color[i][j];
        if (cc != c) {
            t -= k;

        }
        if (t<0){
            return now;
        }
        t += d;
        int l = dps(color, doll, k, i, j + 1, n, m, t, color[i][j]);
        int e = dps(color, doll, k, i + 1, j, n, m, t, color[i][j]);
        int max = l > e ? l : e;
        return max > now ? max : now;
    }
}
