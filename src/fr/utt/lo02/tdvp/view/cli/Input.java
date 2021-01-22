package fr.utt.lo02.tdvp.view.cli;

import java.util.Scanner;

/**
 * This class is an util for handling CLI-based inputs.
 */
public abstract class Input {
    /**
     * The single instance of a Scanner that reads from System.in
     * @see Scanner
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Asks the user to type in the console a string and returns it.
     * @param question The question to display to the console
     * @return The string typed in the console by the user
     */
    public static String promptString(String question) {
        System.out.print("> " + question + " ");
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }

    /**
     * Asks the user to choose from the possible answers. The index of the first anwser will be 1.
     * @param title The title displayed before the possible answers
     * @param answers The possible answers to display
     * @param question The question displayed before asking for the answer
     * @return The index of the answer choosen
     */
    public static int promptChoice(String title, String[] answers, String question) {
        return promptChoice(title, answers, question, 1);
    }

    /**
     * Asks the user to choose from the possible answers
     * @param title The title displayed before the possible answers
     * @param answers The possible answers to display
     * @param question The question displayed before asking for the answer
     * @param firstIndex The index of the first answer
     * @return The index of the answer choosen
     */
    public static int promptChoice(String title, String[] answers, String question, int firstIndex) {
        int choice = 0;
        boolean isValid;

        do {
            // Display the title
            System.out.println("=== " + title + " ===\n");

            // Display answers one by one
            for (int i = 0; i < answers.length; i++) {
                final int displayIndex = i + firstIndex;
                System.out.println(displayIndex + ") " + answers[i]);
            }

            // Ask the user to make a choice
            System.out.print("\n> " + question + " ");

            try {
                choice = scanner.nextInt();

                // Check if the choice is valid
                isValid = choice >= firstIndex && choice < answers.length + firstIndex;
            }
            catch(Exception e) {
                // Certainly a InputMismatchException exception. Anyway, the choice cannot be valid.
                isValid = false;
            }

            // Clear the scanner buffer
            scanner.nextLine();

            // Inform the user that the choice is invalid
            if(!isValid) {
                System.out.println("\nChoix invalide.\n");
            }
        } while(!isValid); // Ask until the answer is valid

        System.out.println();

        return choice;
    }
}
