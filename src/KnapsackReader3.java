import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KnapsackReader3 {
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

        /*
            System.out.println("Dizi 11: " +Arrays.toString(values));


            System.out.println("\nDizi 2: ");
            for (int i = 0; i < weights.length; i++) {
                System.out.print(weights[i] + " ");
            }
        */

        int n = values.length;
        int[][] dp = new int[n+1][maxWeight+1];
        boolean[][] included = new boolean[n+1][maxWeight+1];
        KnapsackReader knapsackReader = new KnapsackReader();


        // Build table in bottom up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= maxWeight; w++) {
                if (i==0 || w==0) {
                    dp[i][w] = 0;
                } else if (weights[i-1] <= w) {
                    int val1 = values[i-1] + dp[i-1][w-weights[i-1]];
                    int val2 = dp[i-1][w];
                    if (val1 > val2) {
                        dp[i][w] = val1;
                        included[i][w] = true;
                    } else {
                        dp[i][w] = val2;
                    }
                } else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        // Print the maximum value that can be obtained
        System.out.println("Maximum value that can be obtained: " + dp[n][maxWeight]);

        // Print the items included in the optimal solution
        List<Integer> items = new ArrayList<>();
        int w = maxWeight;
        for (int i = n; i >= 1; i--) {
            if (included[i][w]) {
                items.add(0);
                w -= weights[i-1];
            }
            else {
                items.add(1);
            }
        }
        System.out.println("Items included in the optimal solution: " + items);

    }


}
