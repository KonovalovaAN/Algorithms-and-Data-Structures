import java.util.*;

public class Task2 {

    // Функция для вычисления f(i) для всех i от 1 до n с использованием sqrt-декомпозиции
    static long[] calculateF(int[] arr, int k) {
        int n = arr.length;
        int blockSize = (int) Math.ceil(Math.sqrt(n)); // размер блока
        int numBlocks = (int) Math.ceil((double) n / blockSize); // количество блоков

        // Создаем массив для хранения минимальных расстояний внутри каждого блока
        long[] minDistances = new long[numBlocks];

        // Заполняем minDistances для каждого блока
        for (int blockId = 0; blockId < numBlocks; blockId++) {
            int startIndex = blockId * blockSize;
            int endIndex = Math.min((blockId + 1) * blockSize, n);
            int[] block = Arrays.copyOfRange(arr, startIndex, endIndex);
            Arrays.sort(block);
            minDistances[blockId] = Long.MAX_VALUE;
            for (int i = 0; i < block.length; i++) {
                long minDistance = Long.MAX_VALUE;
                for (int j = Math.max(0, i - k + 1); j < Math.min(block.length, i + k); j++) {
                    minDistance = Math.min(minDistance, Math.abs(block[i] - block[j]));
                }
                minDistances[blockId] = Math.min(minDistances[blockId], minDistance);
            }
        }

        // Вычисляем f(i) для всех i
        long[] fValues = new long[n];
        for (int i = 0; i < n; i++) {
            int blockId = i / blockSize;
            int startIndex = Math.max(0, blockId * blockSize - k + 1);
            int endIndex = Math.min(n, (blockId + 1) * blockSize + k - 1);
            long minDistance = minDistances[blockId];
            for (int j = startIndex; j < i; j++) {
                minDistance = Math.min(minDistance, Math.abs(arr[i] - arr[j]));
            }
            for (int j = i + 1; j < endIndex; j++) {
                minDistance = Math.min(minDistance, Math.abs(arr[i] - arr[j]));
            }
            fValues[i] = minDistance;
        }

        return fValues;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Вычисление f(i) для всех i от 1 до n
        long[] fValues = calculateF(arr, k);

        // Вывод результатов
        for (long f : fValues) {
            System.out.print(f + " ");
        }
    }
}
