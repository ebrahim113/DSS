import java.util.ArrayList;
import java.util.Scanner;

public class UnderUncerCriteria {

    public static void main(String[] args) {
        ArrayList<String> alts, stateofNature;
        int[][] values;
        Scanner in = new Scanner(System.in);

        alts = new ArrayList<>();
        stateofNature = new ArrayList<>();
        String alt, son;
        int altNumber = 1;

        System.out.println("Enter alternatives names - one at a time (e to exit):");
        do {
            System.out.print("Alternative " + altNumber++ + ": ");
            alt = in.nextLine();
            if (!alt.equalsIgnoreCase("e")) alts.add(alt);
        } while (!alt.equalsIgnoreCase("e"));

        altNumber = 1;
        System.out.println("Enter state of nature names - one at a time (e to exit):");
        do {
            System.out.print("State of Nature " + altNumber++ + ": ");
            son = in.nextLine();
            if (!son.equalsIgnoreCase("e")) stateofNature.add(son);
        } while (!son.equalsIgnoreCase("e"));

        values = new int[alts.size()][stateofNature.size()];

        System.out.println("Enter payoff values for each alternative and state of nature:");
        for (int row = 0; row < alts.size(); row++) {
            for (int col = 0; col < stateofNature.size(); col++) {
                System.out.printf("Payoff for %s under %s: ", alts.get(row), stateofNature.get(col));
                values[row][col] = in.nextInt();
            }
        }

        // Maximax
        int maxPayoff = Integer.MIN_VALUE;
        String maximaxAlt = "";
        for (int row = 0; row < alts.size(); row++) {
            int maxInRow = Integer.MIN_VALUE;
            for (int col = 0; col < stateofNature.size(); col++) {
                if (values[row][col] > maxInRow) {
                    maxInRow = values[row][col];
                }
            }
            if (maxInRow > maxPayoff) {
                maxPayoff = maxInRow;
                maximaxAlt = alts.get(row);
            }
        }
        System.out.println("\nMaximax: " + maximaxAlt + " with payoff " + maxPayoff);

        // Maximin
        int maxMinPayoff = Integer.MIN_VALUE;
        String maximinAlt = "";
        for (int row = 0; row < alts.size(); row++) {
            int minInRow = Integer.MAX_VALUE;
            for (int col = 0; col < stateofNature.size(); col++) {
                if (values[row][col] < minInRow) {
                    minInRow = values[row][col];
                }
            }
            if (minInRow > maxMinPayoff) {
                maxMinPayoff = minInRow;
                maximinAlt = alts.get(row);
            }
        }
        System.out.println("Maximin: " + maximinAlt + " with payoff " + maxMinPayoff);

        // Criterion of Realism (Hurwicz)
        System.out.print("\nEnter coefficient of realism (0 to 1): ");
        double alpha = in.nextDouble();
        double highestHurwiczValue = Double.MIN_VALUE;
        String hurwiczAlt = "";
        for (int row = 0; row < alts.size(); row++) {
            int maxInRow = Integer.MIN_VALUE;
            int minInRow = Integer.MAX_VALUE;
            for (int col = 0; col < stateofNature.size(); col++) {
                if (values[row][col] > maxInRow) maxInRow = values[row][col];
                if (values[row][col] < minInRow) minInRow = values[row][col];
            }
            double weightedAvg = alpha * maxInRow + (1 - alpha) * minInRow;
            if (weightedAvg > highestHurwiczValue) {
                highestHurwiczValue = weightedAvg;
                hurwiczAlt = alts.get(row);
            }
        }
        System.out.println("Hurwicz Criterion: " + hurwiczAlt + " with weighted average " + highestHurwiczValue);

        // Equally Likely (Laplace)
        double highestLaplaceValue = Double.MIN_VALUE;
        String laplaceAlt = "";
        for (int row = 0; row < alts.size(); row++) {
            double avg = 0;
            for (int col = 0; col < stateofNature.size(); col++) {
                avg += values[row][col];
            }
            avg /= stateofNature.size();
            if (avg > highestLaplaceValue) {
                highestLaplaceValue = avg;
                laplaceAlt = alts.get(row);
            }
        }
        System.out.println("Laplace Criterion: " + laplaceAlt + " with average payoff " + highestLaplaceValue);

        // Minimax Regret
        int[][] regretTable = new int[alts.size()][stateofNature.size()];
        for (int col = 0; col < stateofNature.size(); col++) {
            int maxInColumn = Integer.MIN_VALUE;
            for (int row = 0; row < alts.size(); row++) {
                if (values[row][col] > maxInColumn) {
                    maxInColumn = values[row][col];
                }
            }
            for (int row = 0; row < alts.size(); row++) {
                regretTable[row][col] = maxInColumn - values[row][col];
            }
        }

        int minMaxRegret = Integer.MAX_VALUE;
        String minimaxRegretAlt = "";
        for (int row = 0; row < alts.size(); row++) {
            int maxRegret = Integer.MIN_VALUE;
            for (int col = 0; col < stateofNature.size(); col++) {
                if (regretTable[row][col] > maxRegret) {
                    maxRegret = regretTable[row][col];
                }
            }
            if (maxRegret < minMaxRegret) {
                minMaxRegret = maxRegret;
                minimaxRegretAlt = alts.get(row);
            }
        }
        System.out.println("Minimax Regret: " + minimaxRegretAlt + " with regret " + minMaxRegret);
    }
}
