import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.txt")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        String s1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String s2 = st.nextToken();
        StringBuilder str3 = new  StringBuilder (s1 + s1);
        int[] array = new int[str3.length()];
        array = func(array, s2);
        int aaaa = solution(str3, s2, array);
        writer.write(Integer.toString(aaaa));
        writer.close();
        br.close();
    }

    public static int solution(StringBuilder splush,String s2, int[] array) {
        int i = 0;
        int j = 0;
        while (i < splush.length()) {
            if (splush.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            }
            if (j == s2.length()){
                return i - j;
            } else if (i < splush.length() && splush.charAt(i) != s2.charAt(j)) {
                if (j != 0) {
                    j = array[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    public static int[] func (int[] array, String str3) {
        array = new int[str3.length()];
        int j;
        for (int i = 1; i < str3.length(); i++) {
            j = array[i - 1];
            while ((j > 0) && (str3.charAt(i) != str3.charAt(j))){
                j = array [j - 1];
            }
            if (str3.charAt(i) == str3.charAt(j)) {
                j++;
                array[i] = j;
            }
        }
        return array;
    }
}