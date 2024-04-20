import java.io.*;
import java.util.StringTokenizer;

public class IsBinHeap {
    public static void main(String[] args) throws IOException {
        int[] array = readInputArray();
        boolean flag = isBinaryHeap(array);
        writeOutputResult(flag);
    }

    private static int[] readInputArray() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(
                new File("D:\\study\\4 сем\\алгосы\\TA_isBinHeap_0.2\\src\\input.txt")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        return array;
    }

    private static boolean isBinaryHeap(int[] array) {
        int n = array.length - 1;
        for (int i = 1; i <= n / 2; i++) {
            if (2*i <= n && array[i] > array[2*i]){
                return false;
            }
            if (2*i + 1 <= n && array[i] > array[2*i + 1]){
                return false;
            }
        }
        return true;
    }

    private static void writeOutputResult(boolean flag) throws IOException {
        FileWriter writer = new FileWriter(
                new File("D:\\study\\4 сем\\алгосы\\TA_isBinHeap_0.2\\src\\output.txt"));
        if (flag) {
            writer.write("Yes");
        } else {
            writer.write("No");
        }
        writer.close();
    }
}
