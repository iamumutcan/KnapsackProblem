import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Greedy {

    public static void main(String[] args) throws FileNotFoundException {
        int[] values;
        int[] weights ;
        int index = 0;
        int maxWeight;
        int maxValues;

        File dosya = new File("src/ks_19_0");
        Scanner scanner = new Scanner(dosya);
        String sutun = scanner.nextLine();
        String[] baslik = sutun.split(" ");
        maxWeight=Integer.parseInt(baslik[1]);
        maxValues=Integer.parseInt(baslik[0]);
        values = new int[maxValues];
        weights = new int[maxValues];
        while (scanner.hasNextLine()) {
            String satir = scanner.nextLine();
            String[] veriler = satir.split(" ");

            values[index] = Integer.parseInt(veriler[0]);
            weights[index] = Integer.parseInt(veriler[1]);

            index++;
        }
        scanner.close();

        knapsackGreedy(maxWeight, values, weights);
    }
    public static void knapsackGreedy(int maxWeight, int[] values, int[] weights) {
        int n = values.length;

        // Calculate value-to-weight ratios for each item
        double[] ratios = new double[n];
        for (int i = 0; i < n; i++) {
            ratios[i] = (double) values[i] / (double) weights[i];
        }

        // Sort items in decreasing order of value-to-weight ratios
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (ratios[j] > ratios[maxIdx]) {
                    maxIdx = j;
                }
            }
            double tempRatio = ratios[i];
            ratios[i] = ratios[maxIdx];
            ratios[maxIdx] = tempRatio;
            int tempValue = values[i];
            values[i] = values[maxIdx];
            values[maxIdx] = tempValue;
            int tempWeight = weights[i];
            weights[i] = weights[maxIdx];
            weights[maxIdx] = tempWeight;
        }

        // Fill the knapsack with items in order until it is full
        int currentWeight = 0;
        double maxValue = 0;
        for (int i = 0; i < n; i++) {
            if (currentWeight + weights[i] <= maxWeight) {
                currentWeight += weights[i];
                maxValue += values[i];
            } else {
                double remainingWeight = maxWeight - currentWeight;
                maxValue += remainingWeight * ratios[i];
                break;
            }
        }

        System.out.println("Maximum value: " + maxValue);
    }

}
