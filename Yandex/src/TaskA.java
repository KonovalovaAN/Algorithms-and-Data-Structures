import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class TaskA {
    static Map<Point, Integer> points = new HashMap<>();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int posX;
    static int posY;

    static boolean hasConsecutive(int[] sequence) {
        int onesCounter = 0;
        for (int value : sequence) {
            if (Integer.compare(value, 1) == 0) {
                onesCounter = onesCounter + 1;
            } else {
                onesCounter = 0;
            }
            if (Integer.compare(onesCounter, 5)  == 0) {
                return true;
            }
        }
        return false;
    }

    static boolean checkLines() {
        return hasConsecutive(getHorizontal())
                || hasConsecutive(getVertical())
                || hasConsecutive(getDiagonal1())
                || hasConsecutive(getDiagonal2());
    }

    static int[] getHorizontal() {
        int[] horizontal = new int[9];
        for (int i = -4; i <= 4; ++i) {
            if (points.containsKey(new Point(posX, posY + i)) && points.get(new Point(posX, posY + i)).equals(points.get(new Point(posX, posY)))) {
                horizontal[i + 4] = 1;
            }
        }
        return horizontal;
    }

    static int[] getVertical() {
        int[] vertical = new int[9];
        for (int i = -4; i <= 4; ++i) {
            if (points.containsKey(new Point(posX + i, posY)) && points.get(new Point(posX + i, posY)).equals(points.get(new Point(posX, posY)))) {
                vertical[i + 4] = 1;
            }
        }
        return vertical;
    }

    static int[] getDiagonal1() {
        int[] diagonal1 = new int[9];
        for (int i = -4; i <= 4; ++i) {
            if (points.containsKey(new Point(posX + i, posY + i)) && points.get(new Point(posX + i, posY + i)).equals(points.get(new Point(posX, posY)))) {
                diagonal1[i + 4] = 1;
            }
        }
        return diagonal1;
    }

    static int[] getDiagonal2() {
        int[] diagonal2 = new int[9];
        for (int i = -4; i <= 4; ++i) {
            if (points.containsKey(new Point(posX - i, posY + i)) && points.get(new Point(posX - i, posY + i)).equals(points.get(new Point(posX, posY)))) {
                diagonal2[i + 4] = 1;
            }
        }
        return diagonal2;
    }

    static void solve() throws IOException {
        n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            posX = Integer.parseInt(st.nextToken());
            posY = Integer.parseInt(st.nextToken());
            points.put(new Point(posX, posY), i % 2);
            if (checkLines() && i == n - 1) {
                if (i % 2 == 0) {
                    writer.write("First");
                    return;
                } else {
                    writer.write("Second");
                    return;
                }
            } else if (checkLines()) {
                writer.write("Inattention");
                return;
            }
        }
        writer.write("Draw");
    }

    public static void main(String[] args) throws IOException {
        solve();
        reader.close();
        writer.flush();
    }
}
