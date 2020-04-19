import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*  Задача такая:
    Дается N цифр, нужно вывесты все возможные наборы цифр
    Цифры из набора не могут повторяться и идут строго по возрастанию
 */
public class Perebor {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<String> setOfNumbers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ArrayList<String> setOfFigures = new ArrayList<>();
        String consoleNumber;
        int number;
        System.out.println("Вводите цифры, надоест - введете 0");
        do {
            consoleNumber = reader.readLine();
            try {
                number = Integer.parseInt(consoleNumber);
                if (number != 0)
                    setOfFigures.add(consoleNumber);
            } catch (NumberFormatException e){
                System.out.println("Лол, введи число, а не эту чушь");
            }
        } while (!consoleNumber.equals("0"));

        // Вернихний цикл отвечает за число разрядов в числе
        for (int numberOfRank = 1; numberOfRank <= setOfFigures.size(); numberOfRank++){
            // Цикл отвечает за расстояние между выбранными цифрами
            for (int distanceBetween = 1; distanceBetween < 3; distanceBetween++){ // было setOfFigures.size()
                // Цикл отвечает за проход, проверку, сортировку и вставку
                for (int step = 0; step < setOfFigures.size(); step++){
                    String tempNumber = "";
                    int nextIndex = step-1;
                    //Получил число, отсортировал, проверил на входимость, вставил
                    for (int  j = 0; j < numberOfRank; j++) {
                        if (j == 1 )
                            nextIndex = (nextIndex + distanceBetween)%setOfFigures.size();
                        else nextIndex = (nextIndex + 1)%setOfFigures.size();
                        tempNumber += setOfFigures.get(nextIndex);
                    }
                    System.out.print(tempNumber + " ");
                    tempNumber = sortNumber(tempNumber);
                    if (!setOfNumbers.contains(tempNumber)&&!hasTheSameFigure(tempNumber))
                        setOfNumbers.add(tempNumber);
                }
                System.out.print("Distance: " + distanceBetween);
                System.out.println();
            }
        }
        System.out.println(setOfNumbers);
    }

    public static boolean hasTheSameFigure(String tempNumber){
        char[] arr = tempNumber.toCharArray();
        Arrays.sort(arr);
        for(int i = 0; i < arr.length-1; i++)
            if (arr[i] == arr[i+1])
                return true;
        return false;
    }

    public static String sortNumber(String number){
        String sortedNumber = "";
        char[] arr = number.toCharArray();
        Arrays.sort(arr);
        for (char c : arr)
            sortedNumber+=c;
        return sortedNumber;
     }

}
