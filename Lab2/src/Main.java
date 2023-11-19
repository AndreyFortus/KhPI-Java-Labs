import java.util.Scanner;

public class Main {

    public static boolean isEditDistanceWithinLimit(String word1, String word2, int editLimit) {
        int m = word1.length();
        int n = word2.length();

        if (Math.abs(m - n) > editLimit) {
            return false;
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }

        return dp[m][n] <= editLimit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input first word: ");
        String word1 = scanner.nextLine();

        System.out.print("Input second word: ");
        String word2 = scanner.nextLine();

        System.out.print("Input edit distance: ");
        int editLimit = scanner.nextInt();

        boolean result = isEditDistanceWithinLimit(word1, word2, editLimit);

        if (result) {
            System.out.println("String conform to a specified distance");
        } else {
            System.out.println("String do not conform to a specified distance");
        }
    }
}
