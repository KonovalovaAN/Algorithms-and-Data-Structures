import java.io.*;
import java.util.StringTokenizer;

public class MatrixMul {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter writer = new PrintWriter("output.txt");
        int s = Integer.parseInt(st.nextToken());
        int[][] matrixInf = new int[s + 1][2];
        for (int i = 1; i <= s; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                matrixInf[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        writer.print(calculateMatrixWithAnswer(matrixInf, s)[1][s]);
        writer.close();
    }

    public static int[][] calculateMatrixWithAnswer(int[][] matrixInf, int s) {
        int[][] matrixWithAnswer = new int[s + 1][s + 1];
        int cost;

        for (int i = 1; i <= s; i++) {
            for (int j = 1; j <= s; j++) {
                matrixWithAnswer[i][j] = -1;
            }
        }

        for (int i = 1; i <= s; i++) {
            matrixWithAnswer[i][i] = 0;
            if (i != s) {
                matrixWithAnswer[i][i + 1] = matrixInf[i][0] * matrixInf[i][1] * matrixInf[i + 1][1];
            }
        }

        for (int length = 3; length <= s; length++) {
            for (int i = 1; i <= s - length + 1; i++) {
                int j = i + length - 1;
                for (int k = i; k < j; k++) {
                    cost = matrixWithAnswer[i][k] + matrixWithAnswer[k + 1][j] +
                            matrixInf[i][0] * matrixInf[k][1] * matrixInf[j][1];
                    if (matrixWithAnswer[i][j] == -1 || cost < matrixWithAnswer[i][j]) {
                        matrixWithAnswer[i][j] = cost;
                    }
                }
            }
        }

        return matrixWithAnswer;
    }
}
