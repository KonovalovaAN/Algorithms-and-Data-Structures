import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        List<Integer> nodes = new ArrayList<>();
        while (scanner.hasNextInt()) {
            nodes.add(scanner.nextInt());
        }
        scanner.close();
        nodes = nodes.stream().distinct().collect(Collectors.toList());
        long sum = 0;
        for (Integer k : nodes) {
            sum += k;
        }
        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(sum));
        writer.close();
    }
}
