import java.io.*;
import java.util.StringTokenizer;

public class Palindrom {

    public static void main(String[] args) throws IOException {
        String string = readInputFromFile("input.txt");
        int[][] data = findDataMatrix(string);
        writeOutputToFile("output.txt", data, string);
    }

    public static String readInputFromFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    public static int[][] findDataMatrix(String string) {
        int[][] data = new int[string.length()][string.length()];

        for (int i = 0; i < string.length(); i++) {
            data[i][i] = 1;
            if (i < string.length() - 1) {
                if (string.charAt(i) == string.charAt(i + 1)) {
                    data[i][i + 1] = 2;
                } else {
                    data[i][i + 1] = 1;
                }
            }
        }

        for (int length = 2; length < string.length(); length++) {
            for (int i = 0; i < string.length() - length; i++) {
                int j = i + length;
                if (string.charAt(i) == string.charAt(j)) {
                    data[i][j] = data[i + 1][j - 1] + 2;
                } else {
                    data[i][j] = Math.max(data[i + 1][j], data[i][j - 1]);
                }
            }
        }

        return data;
    }

    public static void writeOutputToFile(String filePath, int[][] data, String string) throws IOException {
        FileWriter writer = new FileWriter(new File(filePath));
        writer.write(String.valueOf(data[0][string.length() - 1]));
        writer.write('\n');

        char[] palindromArray = new char[data[0][string.length() - 1]];

        int length = string.length();
        int i = 0;
        int j = length - 1;
        int k = 0;

        while (i <= j) {
            if (string.charAt(i) == string.charAt(j)) {
                palindromArray[k] = string.charAt(i);
                palindromArray[palindromArray.length - k - 1] = string.charAt(j);
                k++;
                i++;
                j--;
            } else if (data[i + 1][j] > data[i][j - 1]) {
                i++;
            } else {
                j--;
            }
        }

        writer.write(palindromArray);
        writer.close();
    }

}
