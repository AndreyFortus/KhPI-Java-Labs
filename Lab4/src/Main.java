import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lab4 Andrii Fortus IKM-221a var18");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(-5, 3 -10, -2, 7, 1, -123, 777));
        System.out.println("Список перед перестановкою: " + numbers);

        int positiveIndex = 0;
        int negativeIndex = numbers.size() - 1;

        int iter = 1;

        while (positiveIndex < negativeIndex) {
            int positiveValue = numbers.get(positiveIndex);
            int negativeValue = numbers.get(negativeIndex);

            if (positiveValue < 0) {
                numbers.set(positiveIndex, negativeValue);
                numbers.set(negativeIndex, positiveValue);
                System.out.println("№ перестановки = "+ iter + " " + numbers);
                iter++;
            }

            if (positiveValue >= 0) {
                positiveIndex++;
            }

            if (negativeValue < 0) {
                negativeIndex--;
            }
        }

        System.out.println("Список після перестановки: " + numbers);
    }
}