import java.io.*;
import java.util.StringTokenizer;

public class LIS {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("output.txt");
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        int[] array = readInputArray(br);
        int max = findLIS(array);

        writer.write(Integer.toString(max));

        writer.close();
        br.close();
    }

    public static int[] readInputArray(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        return array;
    }
    public static int findLIS(int[] array) {
        int n = array.length;
        int[] arrayWithLis = new int[n + 2];
        for (int i = 0; i < n + 2; i++){
            arrayWithLis[i] = Integer.MAX_VALUE;
        }
        arrayWithLis[0] = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arrayWithLis[findLowerBound(arrayWithLis, array[i])] = array[i];
        }
        int max = n + 1;
        while (arrayWithLis[max] == Integer.MAX_VALUE) {
            max--;
        }
        return(max);
    }

    public static int findLowerBound(int[] array, int value) {
        int left = 0;
        int right = array.length;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (array[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}