import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IsBinomHeap {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        long n = scanner.nextLong();
        scanner.close();

        StringBuilder binN = new StringBuilder(Long.toBinaryString(n));
        binN.reverse();

        FileWriter writer = new FileWriter("output.txt");

        int height = 0;
        for (int i = 0; i < binN.length(); i++) {
            if (binN.charAt(i) == '1') {
                writer.write(height + "\n");
            }
            height++;
        }

        writer.close();
    }
}
