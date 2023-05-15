import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Knapsack2 {

    static int maxWeight;
    static int maxValues;
    static int[] values;
    static int[] weights;
    static int n;
    static int maxValue = 0;
    static int[] bestItems;

    public static void main(String[] args) {
        try {
            File file = new File("src/ks_4_0");
            Scanner scanner = new Scanner(file);
            String header = scanner.nextLine();
            String[] headerValues = header.split(" ");
            maxValues = Integer.parseInt(headerValues[0]);
            maxWeight = Integer.parseInt(headerValues[1]);
            values = new int[maxValues];
            weights = new int[maxValues];
            n = maxValues;
            bestItems = new int[n];

            int index = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] items = line.split(" ");

                values[index] = Integer.parseInt(items[0]);
                weights[index] = Integer.parseInt(items[1]);

                index++;
            }
            scanner.close();

            boolean[] selected = new boolean[n];
            branchAndBound(selected);
            System.out.println("Max value: " + maxValue);
            System.out.println("Selected items: ");
            for (int i = 0; i < n; i++) {
                if (bestItems[i] == 1) {
                    System.out.println("Item " + i + " with value " + values[i] + " and weight " + weights[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void branchAndBound(boolean[] selected) {
        int level = 0;
        int valueSoFar = 0;
        int weightSoFar = 0;
        boolean[] parentSelected = new boolean[n];

        while (true) {
            if (level == n) { // Son seviyeye ulaştıysak
                if (valueSoFar > maxValue) { // Şimdiye kadarki maksimum değer
                    maxValue = valueSoFar;
                    for (int i = 0; i < n; i++) {
                        bestItems[i] = parentSelected[i] ? 1 : 0;
                    }
                }
                if (valueSoFar == maxValue) { // Aynı maksimum değere sahip farklı çözümler var
                    int sum1 = 0;
                    int sum2 = 0;
                    for (int i = 0; i < n; i++) {
                        if (parentSelected[i]) {
                            sum1 += weights[i];
                        } else if (selected[i]) {
                            sum2 += weights[i];
                        }
                    }
                    if (sum1 < sum2) {
                        for (int i = 0; i < n; i++) {
                            bestItems[i] = parentSelected[i] ? 1 : 0;
                        }
                    }
                }

                if (level == 0) {
                    return; // İlk seviyedeyiz, tüm çözümler tamamlandı
                }

                level--; // Geri dön
                valueSoFar -= values[level];
                weightSoFar -= weights[level];
                continue;
            }

            if (weightSoFar + weights[level] <= maxWeight) { // Eşyayı alıyoruz
                selected[level] = true;
                level++;
                valueSoFar += values[level - 1];
                weightSoFar += weights[level - 1];
                for (int i = level; i < n; i++) {
                    parentSelected[i] = selected[i];
                }
            } else { // Eşyayı almıyoruz
                selected[level] = false;
                level++;
                for (int i = level; i < n; i++) {
                    parentSelected[i] = selected[i];
                }
            }
        }
    }
}
