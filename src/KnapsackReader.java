import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class KnapsackReader {
    public static void main(String[] args) throws FileNotFoundException {
   

        int[] values;
        int[] dizi2 ;
        int index = 0;
        int maxWeights;
        int maxValues;

            File dosya = new File("src/ks_4_0");
            Scanner scanner = new Scanner(dosya);
            String sutun = scanner.nextLine();
            String[] baslik = sutun.split(" ");
            maxWeights=Integer.parseInt(baslik[1]);
            maxValues=Integer.parseInt(baslik[0]);
            values = new int[maxValues];
            dizi2 = new int[maxValues];
            while (scanner.hasNextLine()) {
                String satir = scanner.nextLine();
                String[] veriler = satir.split(" ");

                values[index] = Integer.parseInt(veriler[0]);
                dizi2[index] = Integer.parseInt(veriler[1]);

                index++;
            }
            scanner.close();


        System.out.println("Dizi 1: " +Arrays.toString(values));


        System.out.println("\nDizi 2: ");
        for (int i = 0; i < dizi2.length; i++) {
            System.out.print(dizi2[i] + " ");
        }

    }


}
