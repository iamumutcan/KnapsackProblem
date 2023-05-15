import java.util.*;

public class Main {
    public static void main(String[] args) {
        int maxWeight = 11;
        int[] values = {8, 10, 15, 4};
        int[] weights = {4, 5, 8, 3};
        int n = values.length;
        int[][] dp = new int[n+1][maxWeight+1];
        boolean[][] included = new boolean[n+1][maxWeight+1];


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
                items.add(1);
                w -= weights[i-1];
            }
            else {
                items.add(0);
            }
        }
        Collections.reverse(items);
        System.out.println("Items included in the optimal solution: " + items);
    }
}