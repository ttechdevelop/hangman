import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman{
    public static void main (String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/words/russian_nouns.txt"));
        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));



        //System.out.println(word); // DELETE AFTER TESTING

        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;
        while(true) {

            if(printWordState(word, playerGuesses)) {
                System.out.println("Победа!");
                break;
            }
            printHangman(wrongCount);

            if(!PlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;
            }

        }

        if (wrongCount >= 6) {
            System.out.println("Повешен!");
        }




    }

    public static void printHangman(Integer wrongCount) {

        System.out.println();
        System.out.println(" --------");
        System.out.println(" |      |");

        if (wrongCount >= 1){
            System.out.println(" O");
        }
        if (wrongCount >= 2) {
            System.out.print("\\");
        }
        if (wrongCount >= 3) {
            System.out.print(" /\n");
        }
        if (wrongCount >= 4) {
            System.out.println(" |");
        }
        if (wrongCount >= 5) {
            System.out.print("/");
        }
        if (wrongCount >= 6) {
            System.out.println(" \\");
        }
    }

    public static boolean PlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println();
        System.out.print("Введите букву: ");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return (word.contains(letterGuess));
    }

    public static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {

            if(playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }

}
