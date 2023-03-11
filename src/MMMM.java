import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yxl
 * @date 2023/3/11 下午8:03
 */
//时间限制： 3000MS
//内存限制： 589824KB
//题目描述：
//小D和小W最近在玩坦克大战，双方操控自己的坦克在16*16的方格图上战斗，小D的坦克初始位置在地图的左上角，朝向为右，其坐标(0,0)，
// 小W的坦克初始位置在地图右下角，朝向为左，坐标为(15,15)。坦克不能移动到地图外，坦克会占领自己所在的格子，己方的坦克不可以进入对方占领过的格子。
// 每一个回合双方必须对自己的坦克下达以下5种指令中的一种：
//
//• 移动指令U：回合结束后，使己方坦克朝向为上，若上方的格子未被对方占领，则向当前朝向移动一个单位（横坐标-1），否则保持不动；
//
//• 移动指令D：回合结束后，使己方坦克朝向为下，若下方的格子未被对方占领，则向当前朝向移动一个单位（横坐标+1），否则保持不动；
//
//• 移动指令L：回合结束后，使己方坦克朝向为左，若左侧的格子未被对方占领，则向当前朝向移动一个单位（纵坐标-1），否则保持不动；
//
//• 移动指令R：回合结束后，使己方坦克朝向为右，若右侧的格子未被对方占领，则向当前朝向移动一个单位（纵坐标+1），否则保持不动；
//
//• 开火指令F：己方坦克在当前回合立即向当前朝向开火；
//
//己方坦克开火后，当前回合己方坦克的正前方若有对方的坦克，对方的坦克将被摧毁，游戏结束，己方获得胜利；若双方的坦克在同一回合被摧毁，游戏结束，
// 判定为平局；若双方的坦克在同一回合内进入到同一个未被占领的格子，则双方的坦克发生碰撞，游戏结束，判定为平局；当游戏进行到第256个回合后，游戏结束
// ，若双方坦克均未被摧毁，则占领格子数多的一方获得胜利，若双方占领的格子数一样多，判定为平局。 * 注意，若一方开火，另一方移动，则认为是先开火
// ，后移动。
//
//现在小D和小W各自给出一串长度为256的指令字符串，请你帮助他们计算出游戏将在多少个回合后结束，以及游戏的结果。
public class MMMM {
    public static void main(String[] args) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        try {
            char winner = 'Y';
            char[] D = read.readLine().toCharArray();
            char[] W = read.readLine().toCharArray();
            char[][] map = new char[16][16];
            int dx = 0, dy = 0, dc = 3;
            int wx = 15, wy = 15, wc = 1;
            map[0][0] = 'D';
            map[15][15] = 'W';
            int i = 0;
            for (; i < 256; i++) {
                char d = D[i];
                char w = W[i];
                if (d == 'F' && w == 'F') {
                    boolean df = F(dx, dy, dc, wx, wy);
                    boolean wf = F(wx, wy, wc, dx, dy);
                    if (df && wf) {
                        winner = 'P';
                        break;
                    } else if (df) {
                        winner = 'D';
                        break;
                    } else if (wf) {
                        winner = 'W';
                        break;
                    }
                } else if (d == 'F' || w == 'F') {
                    if (d == 'F') {
                        boolean df = F(dx, dy, dc, wx, wy);
                        if (df) {
                            winner = 'D';
                            break;
                        } else {
                            switch (w) {
                                case 'U': {
                                    int[] ws = U(map, 'W', wx, wy);
                                    wx = ws[0];
                                    wy = ws[1];
                                    wc = ws[2];
                                    break;
                                }

                                case 'D': {
                                    int[] ws = D(map, 'W', wx, wy);
                                    wx = ws[0];
                                    wy = ws[1];
                                    wc = ws[2];
                                    break;
                                }
                                case 'L': {
                                    int[] ws = L(map, 'W', wx, wy);
                                    wx = ws[0];
                                    wy = ws[1];
                                    wc = ws[2];
                                    break;
                                }
                                case 'R': {
                                    int[] ws = R(map, 'W', wx, wy);
                                    wx = ws[0];
                                    wy = ws[1];
                                    wc = ws[2];
                                    break;
                                }
                            }
                        }
                    } else {
                        boolean wf = F(wx, wy, wc, dx, dy);
                        if (wf) {
                            winner = 'D';
                            break;
                        } else {
                            switch (d) {
                                case 'U': {
                                    int[] ws = U(map, 'D', dx, dy);
                                    dx = ws[0];
                                    dy = ws[1];
                                    dc = ws[2];
                                    break;
                                }

                                case 'D': {
                                    int[] ws = D(map, 'D', dx, dy);
                                    dx = ws[0];
                                    dy = ws[1];
                                    dc = ws[2];
                                    break;
                                }
                                case 'L': {
                                    int[] ws = L(map, 'D', dx, dy);
                                    dx = ws[0];
                                    dy = ws[1];
                                    dc = ws[2];
                                    break;
                                }
                                case 'R': {
                                    int[] ws = R(map, 'D', dx, dy);
                                    dx = ws[0];
                                    dy = ws[1];
                                    dc = ws[2];
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    switch (w) {
                        case 'U': {
                            int[] ws = U(map, 'W', wx, wy);
                            wx = ws[0];
                            wy = ws[1];
                            wc = ws[2];
                            break;
                        }

                        case 'D': {
                            int[] ws = D(map, 'W', wx, wy);
                            wx = ws[0];
                            wy = ws[1];
                            wc = ws[2];
                            break;
                        }
                        case 'L': {
                            int[] ws = L(map, 'W', wx, wy);
                            wx = ws[0];
                            wy = ws[1];
                            wc = ws[2];
                            break;
                        }
                        case 'R': {
                            int[] ws = R(map, 'W', wx, wy);
                            wx = ws[0];
                            wy = ws[1];
                            wc = ws[2];
                            break;
                        }
                    }
                    switch (d) {
                        case 'U': {
                            int[] ws = U(map, 'D', dx, dy);
                            dx = ws[0];
                            dy = ws[1];
                            dc = ws[2];
                            break;
                        }

                        case 'D': {
                            int[] ws = D(map, 'D', dx, dy);
                            dx = ws[0];
                            dy = ws[1];
                            dc = ws[2];
                            break;
                        }
                        case 'L': {
                            int[] ws = L(map, 'D', dx, dy);
                            dx = ws[0];
                            dy = ws[1];
                            dc = ws[2];
                            break;
                        }
                        case 'R': {
                            int[] ws = R(map, 'D', dx, dy);
                            dx = ws[0];
                            dy = ws[1];
                            dc = ws[2];
                            break;
                        }
                    }
                }
            }
            if (winner == 'Y') {
                int d = 0, w = 0;
                for (int z = 0; z < 16; z++) {
                    for (int j = 0; j < 16; j++) {
                        if (map[z][j] == 'D') {
                            d++;
                        }
                        if (map[z][j] == 'W') {
                            w++;
                        }
                    }
                }
                if (d > w) {
                    winner = 'D';
                } else if (d < w) {
                    winner = 'W';
                } else {
                    winner = 'P';
                }
                System.out.println(i);
            } else {
                System.out.println(i + 1);
            }
            System.out.println(winner);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //zuo  1  shang->2  you->3  xia->4
    public static int[] U(char[][] map, char people, int x, int y) {
        int[] now = new int[3];
        if (x - 1 < 0) {
            now[0] = x;
            now[1] = y;
            now[2] = 2;
        } else if (map[x - 1][y] == 0) {
            map[x - 1][y] = people;
            now[0] = x - 1;
            now[1] = y;
            now[2] = 2;
        } else if (map[x - 1][y] == people) {
            now[0] = x - 1;
            now[1] = y;
            now[2] = 2;
        } else {
            now[0] = x;
            now[1] = y;
            now[2] = 2;
        }
        return now;
    }

    public static int[] D(char[][] map, char people, int x, int y) {
        int[] now = new int[3];
        if (x + 1 > 15) {
            now[0] = x;
            now[1] = y;
            now[2] = 4;
        } else if (map[x + 1][y] == 0) {
            map[x + 1][y] = people;
            now[0] = x + 1;
            now[1] = y;
            now[2] = 4;
        } else if (map[x + 1][y] == people) {
            now[0] = x + 1;
            now[1] = y;
            now[2] = 4;
        } else {
            now[0] = x;
            now[1] = y;
            now[2] = 4;
        }
        return now;
    }

    public static int[] L(char[][] map, char people, int x, int y) {
        int[] now = new int[3];
        if (y - 1 < 0) {
            now[0] = x;
            now[1] = y;
            now[2] = 1;
        } else if (map[x][y - 1] == 0) {
            map[x][y - 1] = people;
            now[0] = x;
            now[1] = y - 1;
            now[2] = 1;
        } else if (map[x][y - 1] == people) {
            now[0] = x;
            now[1] = y - 1;
            now[2] = 1;
        } else {
            now[0] = x;
            now[1] = y;
            now[2] = 1;
        }
        return now;
    }

    public static int[] R(char[][] map, char people, int x, int y) {
        int[] now = new int[3];
        if (y + 1 > 15) {
            now[0] = x;
            now[1] = y;
            now[2] = 3;
        } else if (map[x][y + 1] == 0) {
            map[x][y + 1] = people;
            now[0] = x;
            now[1] = y + 1;
            now[2] = 3;
        } else if (map[x][y + 1] == people) {
            now[0] = x;
            now[1] = y + 1;
            now[2] = 3;
        } else {
            now[0] = x;
            now[1] = y;
            now[2] = 3;
        }
        return now;
    }

    public static boolean F(int x, int y, int cx, int nx, int ny) {
        switch (cx) {
            case 1:
                y -= 1;
                break;
            case 2:
                x -= 1;
                break;
            case 3:
                y += 1;
                break;
            case 4:
                x += 1;
                break;
        }
        return nx == x && ny == y;
    }
}
