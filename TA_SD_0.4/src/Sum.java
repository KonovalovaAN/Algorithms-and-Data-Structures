import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.sqrt;

public class Sum {

    public static void main(String[] args) throws IOException {
        Sum sum = new Sum();
        sum.solveQueries();
    }

    private long[] array;
    private long[] blocksSums;
    private int len;

    public void solveQueries() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        array = new long[n];
        blocksSums = new long[n];
        len = (int) (sqrt(n + .0) + 1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Long.parseLong(st.nextToken());
            blocksSums[i / len] += array[i];
        }

        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            String request = st.nextToken();

            if (request.equals("Add")) {
                handleAddQuery(Integer.parseInt(st.nextToken()),
                        Long.parseLong(st.nextToken()));
            } else {
                System.out.println(handleFindSumQuery(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())));
            }
        }

        br.close();
    }

    private void handleAddQuery(int left, long val) {
        array[left] += val;
        int blockIndex = left / len;
        blocksSums[blockIndex] += val;
    }

    private long handleFindSumQuery(int left, int right) {
        long sum = 0;
        int startBlock = left / len;
        int endBlock = right / len;

        if (startBlock == endBlock) {
            for (int i = left; i < right; i++) {
                sum += array[i];
            }
        } else {
            for (int i = left; i < (startBlock + 1) * len; i++) {
                sum += array[i];
            }
            for (int i = startBlock + 1; i < endBlock; i++) {
                sum += blocksSums[i];
            }
            for (int i = endBlock * len; i < right; i++) {
                sum += array[i];
            }
        }
        return sum;
    }
}
