import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Math.sqrt;

public class Main {
    public static int counter = 0;
    public static int len = 0;
    public static int[][] resultTable;
    public static int[] arrayForResearch;
    public static int[] blocks;
    public static int min;
    public static int startBlock;
    public static int endBlock;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.txt")));

        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        resultTable = new int[n + 1][4];
        arrayForResearch = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                resultTable[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(resultTable, Comparator.comparingInt(a -> a[1]));

        len = (int) (sqrt(n + .0) + 1);
        blocks = new int[len + 1];

        for (int i = 1; i <= n; i++) {
            arrayForResearch[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= len; i++) {
            blocks[i] = Integer.MAX_VALUE;
        }

        int locMin;

        for (int i = 1; i <= n; i++) {
            locMin = handleFindMinQuery(1, resultTable[i][2]);
            if (locMin > resultTable[i][3]) {
                counter++;
                arrayForResearch[resultTable[i][2]] = resultTable[i][3];
                updateBlocks(resultTable[i][2]);
            }
        }

        writer.write(Integer.toString(counter));

        writer.close();
    }

    private static void updateBlocks(int index) {
        int blockIndex = index / len;
        blocks[blockIndex] = Math.min(blocks[blockIndex], arrayForResearch[index]);
    }

    private static int handleFindMinQuery(int left, int right) {
        min = Integer.MAX_VALUE;
        startBlock = left / len;
        endBlock = right / len;

        if (startBlock == endBlock) {
            for (int i = left; i <= right; i++) {
                min = Math.min(min, arrayForResearch[i]);
            }
        } else {
            for (int i = left; i < (startBlock + 1) * len; i++) {
                min = Math.min(min, arrayForResearch[i]);
            }
            for (int i = (startBlock + 1) ; i < endBlock; i++) {
                min = Math.min(min, blocks[i]);
            }
            for (int i = endBlock * len; i <= right; i++) {
                min = Math.min(min, arrayForResearch[i]);
            }
        }
        return min;
    }
}