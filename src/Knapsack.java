import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Knapsack {

    static int maxWeight;
    static int maxValues;
    static int[] values;
    static int[] weights;
    static int n;
    static int maxValue = 0;
    static int[] bestItems;

    public static void main(String[] args) {
        try {
            File file = new File("src/ks_10000_0");
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
            branchAndBound(0, 0, 0, selected);
            System.out.println("Max value: " + maxValue);
            System.out.print("Selected items: [ ");
            for(int i=0; i<n; i++) {
                if(bestItems[i] == 1) {
                   // System.out.println("Item " + i + " with value " + values[i] + " and weight " + weights[i]);
                    System.out.print("1 ");
                }
                else{
                    System.out.print("0 ");
                }
            }
            System.out.print("]");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void branchAndBound(int level, int valueSoFar, int weightSoFar, boolean[] selected) {
        if(weightSoFar > maxWeight) { // Sınırı aştıysak geri dön
            return;
        }
        if(level == n) { // Son seviyeye ulaştıysak
            if(valueSoFar > maxValue) { // Şimdiye kadarki maksimum değerden daha iyiyse
                maxValue = valueSoFar;
                for(int i=0; i<n; i++) {
                    if(selected[i]) {
                        bestItems[i] = 1;
                    } else {
                        bestItems[i] = 0;
                    }
                }
            }
            return;
        }
        // Seçilen öğe olmadan alt düğüme git
        selected[level] = false;
        branchAndBound(level+1, valueSoFar, weightSoFar, selected);
        // Seçilen öğeyle alt düğüme git
        selected[level] = true;
        branchAndBound(level+1, valueSoFar+values[level], weightSoFar+weights[level], selected);
    }
}
