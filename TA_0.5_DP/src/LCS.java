import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LCS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] first = readArray(br, n);
        int[] second = readArray(br, n);
        int[][] data = calculateLCS(first, second, n);
        System.out.println(data[n][n]);
        int[][] answer = findLCSIndices(first, second, data, n);
        printAnswer(answer, data, n);
    }

    private static int[] readArray(BufferedReader br, int n) throws IOException {
        int[] array = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        return array;
    }

    private static int[][] calculateLCS(int[] first, int[] second, int n) {
        int[][] data = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (first[i] != second[j]) {
                    data[i][j] = Math.max(data[i - 1][j], data[i][j - 1]);
                } else {
                    data[i][j] = data[i - 1][j - 1] + 1;
                }
            }
        }
        return data;
    }

    private static int[][] findLCSIndices(int[] first, int[] second, int[][] data, int n) {
        int[][] answer = new int[2][data[n][n] + 1];
        int i = n, j = n, k = data[n][n];
        while (data[i][j] > 0) {
            if (first[i] == second[j]) {
                answer[0][k] = i;
                answer[1][k] = j;
                k--;
                i--;
                j--;
            } else if (data[i - 1][j] > data[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return answer;
    }

    private static void printAnswer(int[][] answer, int[][] data, int n) {
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= data[n][n]; j++) {
                System.out.print(answer[i][j] - 1 + " ");
            }
            System.out.println();
        }
    }
}
