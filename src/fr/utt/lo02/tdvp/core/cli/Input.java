package fr.utt.lo02.tdvp.core.cli;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    private Input() {}

    /**
     * Returns the unique scanner of the application
     * @return the instance of the scanner
     */
    public static Scanner getScanner() {
        return scanner;
    }
}
