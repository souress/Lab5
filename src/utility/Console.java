package utility;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Console {
    private final Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void printLine(Object toOut) {
        System.out.println(toOut);
    }

    public String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        } catch (NoSuchElementException ex) {
            System.exit(0);
            line = null;
        }
        if (line.length() == 0) {
            line = null;
        }
        return line;
    }
}
