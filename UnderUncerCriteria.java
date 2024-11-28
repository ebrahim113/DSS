import java.util.ArrayList;
import java.util.Scanner;

public class UnderUncerCriteria {
    public static void main(String[] args) {
        // Declare array of strings for alternatives and states of nature
        ArrayList<String> alts, stateofNature;
        int[][] values;
        double coefficient = .8;

        Scanner in = new Scanner(System.in);

        alts = new ArrayList<>();
        stateofNature = new ArrayList<>();
        String alt, son;
        int altNumber = 1;

        System.out.println("Enter alternatives names - one by time (e) to exit");
        do {
            System.out.print("Please enter alternative number " + altNumber++ + ": ");
            alt = in.nextLine();
            if (alt.charAt(0) != 'e')
                alts.add(alt);
        } while (alt.charAt(0) != 'e');

        altNumber = 1;
        System.out.println("Enter state of nature names one by time (e) to exit");
        do {
            System.out.print("Please enter state of nature number " + altNumber++ + ": ");
            son = in.nextLine();
            if (son.charAt(0) != 'e')
                stateofNature.add(son);
        } while (son.charAt(0) != 'e');

        values = new int[alts.size()][stateofNature.size()];

        // Assign value of each alt
        for (int row = 0; row < alts.size(); row++) {
            for (int col = 0; col < stateofNature.size(); col++) {
                System.out.printf("Enter value of alt: %s - state of nature: %s : ", alts.get(row),
                        stateofNature.get(col));
                values[row][col] = in.nextInt();
            }
        }

        // Maximax
        int maxNumber = Integer.MIN_VALUE;
        int rowmm = 0, colmm = 0;
        // get the lowest number in the first row
        for (int i = 0; i < alts.size(); i++) {
            for (int k = 0; k < stateofNature.size(); k++) {
                if (values[i][k] > maxNumber) {
                    maxNumber = values[i][k];
                    rowmm = i;
                    colmm = k;
                }
            }
        }

        // Maximin
        int minNumber = Integer.MAX_VALUE;
        int rowmmi = 0, colmmi = 0;
        ArrayList<Integer> rowsMinNumbers = new ArrayList<>();
        for (int i = 0; i < alts.size(); i++) {
            for (int k = 0; k < stateofNature.size(); k++) {
                if (values[i][k] < minNumber) {
                    rowmmi = i;
                    colmmi = k;
                    minNumber = values[i][k];
                }
            }
            rowsMinNumbers.add(minNumber);
            minNumber = Integer.MAX_VALUE;
        }

        int maxNumber2 = Integer.MIN_VALUE;
        for (int i : rowsMinNumbers) {
            if (i > maxNumber2) {
                maxNumber2 = i;
            }
        }

        int[] minNumbersInTheRows = new int[alts.size()];
        int[] maxNumbersInTheRows = new int[alts.size()];
        int minNumber3 = Integer.MAX_VALUE;
        int maxNumber3 = Integer.MIN_VALUE;
        for (int i = 0; i < alts.size(); i++) {
            for (int k = 0; k < stateofNature.size(); k++) {
                if (values[i][k] != 0) {
                    if (values[i][k] < minNumber3) {
                        minNumbersInTheRows[i] = values[i][k];
                        // minNumber3 = values[i][k];
                    }
                    if (values[i][k] > maxNumber3) {
                        maxNumbersInTheRows[i] = values[i][k];
                    }
                } else {
                    minNumbersInTheRows[i] = 0;
                    maxNumbersInTheRows[i] = 0;
                }
            }
        }

        for (int i : minNumbersInTheRows) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : maxNumbersInTheRows) {
            System.out.print(i + " ");
        }

    }

}