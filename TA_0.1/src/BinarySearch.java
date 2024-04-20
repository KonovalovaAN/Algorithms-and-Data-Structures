import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int array[] = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int requests[] = new int[k];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            System.out.print(binarySearch(array, requests[i]) + " ");
            System.out.print(findUpperBound(array, requests[i]) + " ");
            System.out.println(findLowerBound(array, requests[i]));
        }
        br.close();
    }

    private static byte binarySearch(int[] array, int value) {
        int left = 0;
        int right = array.length;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (array[mid] == value) {
                return 1;
            } else if (array[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return 0;
    }

    private static int findLowerBound(int[] array, int value) {
        int left = 0;
        int right = array.length;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (array[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static int findUpperBound(int[] array, int value) {
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
