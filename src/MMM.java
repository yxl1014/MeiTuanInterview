import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author yxl
 * @date 2023/3/11 下午7:48
 */

/*
小美是一位天文爱好者，她收集了接下来一段时间中所有会划过她所在的观测地上空的流星信息。
具体地，她收集了n个流星在她所在观测地上空的出现时刻和消失时刻。对于一个流星，若其的出现时刻为s，
消失时刻为t，那么小美在时间段[s, t]都能够观测到它。对于一个时刻，观测地上空出现的流星数量越多，则小美认为该时刻越好。
小美希望能够选择一个最佳的时刻进行观测和摄影，使她能观测到最多数量的流星。现在小美想知道，
在这个最佳时刻，她最多能观测到多少个流星以及一共有多少个最佳时刻可供她选择。

第一行是一个正整数n，表示流星的数量。

第二行是n个用空格隔开的正整数，第i个数si表示第i个流星的出现时间。

第三行是n个用空格隔开的正整数，第i个数ti表示第i个流星的消失时间。

1<=n<=100000, 1<=si<=ti<=10^9
* */
public class MMM {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n;
        try {
            n = Integer.parseInt(reader.readLine());
            int[] s = new int[n];
            String[] s1 = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                s[i] = Integer.parseInt(s1[i]);
            }
            int[] t = new int[n];
            s1 = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                t[i] = Integer.parseInt(s1[i]);
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int j = s[i]; j <= t[i]; j++) {
                    if (map.get(j) == null) {
                        map.put(j, 1);
                    } else {
                        map.put(j, map.get(j) + 1);
                    }
                }
            }
            List<Integer> list = new ArrayList<>(map.values());
            Collections.sort(list);
            int num = 0;
            int max = list.get(list.size() - 1);
            for (int l = list.size() - 1; l >= 0; l--) {
                if (list.get(l) == max) {
                    num++;
                } else {
                    break;
                }
            }
            System.out.println(max + " " + num);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
