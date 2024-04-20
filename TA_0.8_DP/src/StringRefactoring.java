import java.io.*;
import java.util.StringTokenizer;

public class StringRefactoring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("in.txt")));
        FileWriter writer = new FileWriter(new File("out.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int z = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String string1 = st.nextToken();

        st = new StringTokenizer(br.readLine());
        String string2 = st.nextToken();

        int[][] matrixInfo = new int[string1.length() + 2][string2.length() + 2];

        for (int i = 0; i <= string2.length(); i++) {
            matrixInfo[0][i] = i * y;
        }
        for (int i = 0; i <= string1.length(); i++) {
            matrixInfo[i][0] = i * x;
        }

        for (int i = 1; i <= string1.length(); i++) {
            for (int j = 1; j <= string2.length(); j++) {
                if (string1.charAt(i - 1) == string2.charAt(j - 1))
                    matrixInfo[i][j] = matrixInfo[i - 1][j - 1];
                else
                    matrixInfo[i][j] = Math.min(rep(i, j, matrixInfo, z), Math.min(
                            del(i, j, matrixInfo, x),
                            ins(i, j, matrixInfo, y)));
            }
        }

        writer.write(Integer.toString(matrixInfo[string1.length()][string2.length()]));
        writer.close();
        br.close();
    }

    public static int rep(int i, int j, int[][] m, int z) {
        return m[i - 1][j - 1] + z;
    }

    public static int del(int i, int j, int[][] m, int x) {
        return m[i - 1][j] + x;
    }

    public static int ins(int i, int j, int[][] m, int y) {
        return m[i][j - 1] + y;
    }
}
