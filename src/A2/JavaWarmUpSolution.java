package A2;
import java.util.Scanner;

public class JavaWarmUpSolution {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // Predefined categories
        String[] categoriesList = {"phone", "laptop", "smart_watch"};

        // Read the number of items to be entered
        int n = s.nextInt();
        s.nextLine();  // Consume the remaining newline character

        // Declare arrays to store item attributes
        String[] dateT = new String[n];
        String[] timeT = new String[n];
        String[] categoryT = new String[n];
        double[] Assembling_fee = new double[n];
        int[] quantityT = new int[n];
        double[] Assembling_Time = new double[n];
        double[] Energy_and_Device_Cost = new double[n];

        // Read item data from the console and store it in arrays
        for (int i = 0; i < n; i++) {
            String line = s.nextLine();
            String[] parts = line.split(" ");
            dateT[i] = parts[0];
            timeT[i] = parts[1];
            categoryT[i] = parts[2];
            Assembling_fee[i] = Double.parseDouble(parts[3]);
            quantityT[i] = Integer.parseInt(parts[4]);
            Assembling_Time[i] = Double.parseDouble(parts[5]);
            Energy_and_Device_Cost[i] = Double.parseDouble(parts[6]);
        }

        // Find the highest and lowest assembling fees
        int highestItemIndex = getMaxPriceIndex(Assembling_fee);
        int lowestItemIndex = getMinPriceIndex(Assembling_fee);

        // Output details of the highest and lowest assembling fees
        System.out.println(dateT[highestItemIndex]);
        System.out.println(timeT[highestItemIndex]);
        System.out.println(categoryT[highestItemIndex]);
        System.out.println(Assembling_fee[highestItemIndex]);

        System.out.println(dateT[lowestItemIndex]);
        System.out.println(timeT[lowestItemIndex]);
        System.out.println(categoryT[lowestItemIndex]);
        System.out.println(Assembling_fee[lowestItemIndex]);

        // Declare arrays to hold category-wise statistics
        int[] numOfCategoriesC = new int[categoriesList.length];
        double[] totPriceC = new double[categoriesList.length];
        int[] totQuantityC = new int[categoriesList.length];
        double[] totAssembling_TimeC = new double[categoriesList.length];
        double[] totEnergy_and_Device_CostC = new double[categoriesList.length];

        // Loop through all items to update category-wise statistics
        for (int i = 0; i < n; i++) {
            int catIndex = -1;
            for (int j = 0; j < categoriesList.length; j++) {
                if (categoryT[i].equals(categoriesList[j])) {
                    catIndex = j;
                    break;
                }
            }

            numOfCategoriesC[catIndex]++;
            totPriceC[catIndex] += Assembling_fee[i] * quantityT[i];
            totQuantityC[catIndex] += quantityT[i];
            totAssembling_TimeC[catIndex] += Assembling_Time[i];
            totEnergy_and_Device_CostC[catIndex] += Energy_and_Device_Cost[i];
        }

        // Calculate and print category-wise statistics
        for (int i = 0; i < categoriesList.length; i++) {
            System.out.println(categoriesList[i]);
            System.out.println(totQuantityC[i]);
            System.out.printf("%.2f%n", totPriceC[i] / totQuantityC[i]);
            System.out.printf("%.2f%n", (totPriceC[i] - totEnergy_and_Device_CostC[i]-totAssembling_TimeC[i]*16) / totQuantityC[i]);
        }
    }

    // Find the index of the item with the highest assembling fee
    static int getMaxPriceIndex(double[] priceT) {
        int index = 0;
        double highest = priceT[0];
        for (int i = 1; i < priceT.length; i++) {
            if (priceT[i] > highest) {
                highest = priceT[i];
                index = i;
            }
        }
        return index;
    }

    // Find the index of the item with the lowest assembling fee
    static int getMinPriceIndex(double[] priceT) {
        int index = 0;
        double lowest = priceT[0];
        for (int i = 1; i < priceT.length; i++) {
            if (priceT[i] < lowest) {
                lowest = priceT[i];
                index = i;
            }
        }
        return index;
    }
}
