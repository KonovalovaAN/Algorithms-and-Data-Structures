import java.io.*;
import java.util.StringTokenizer;

public class Lenta {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";
        int[] array = readArrayFromFile(inputFilePath);
        int maxScore = findMaxScore(array);
        writeResultToFile(outputFilePath, maxScore);
    }

    public static int[] readArrayFromFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] array = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        return array;
    }

    public static void writeResultToFile(String filePath, int result) throws IOException {
        FileWriter writer = new FileWriter(new File(filePath));
        writer.write(Integer.toString(result));
        writer.close();
    }

    public static int findMaxScore(int[] array) {
        int n = array.length - 1;
        int[][] matrixWithMaxScores = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            matrixWithMaxScores[i][i] = array[i];
            if (i != n) {
                matrixWithMaxScores[i][i + 1] = Math.max(array[i], array[i + 1]);
            }
        }

        int j;

        for (int length = 3; length <= n; length++) {
            for (int i = 1; i <= n - length + 1; i++) {
                j = i + length - 1;
                matrixWithMaxScores[i][j] = Math.max(
                        array[i] + Math.min(matrixWithMaxScores[i + 1][j - 1], matrixWithMaxScores[i + 2][j]),
                        array[j] + Math.min(matrixWithMaxScores[i + 1][j - 1], matrixWithMaxScores[i][j - 2])
                );
            }
        }

        return matrixWithMaxScores[1][n];
    }
}