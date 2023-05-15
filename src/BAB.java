import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BAB {
    static int maxWeight = 11;
    static int[] values = {8, 10, 15, 4};
    static int[] weights = {4, 5, 8, 3};
    static int n = values.length;
    static int maxValue = 0;
    static int[] bestItems = new int[n];

    public static void main(String[] args) throws FileNotFoundException {
        int[] values;
        int[] weights ;
        int index = 0;
        int maxWeight;
        int maxValues;

        File dosya = new File("src/ks_4_0");
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

        boolean[] selected = new boolean[n];
        branchAndBound(0, 0, 0, selected);
        System.out.println("Max value: " + maxValue);
        System.out.println("Selected items: ");
        for(int i=0; i<n; i++) {
            if(bestItems[i] == 1) {
                System.out.println("Item " + i + " with value " + values[i] + " and weight " + weights[i]);
            }
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
