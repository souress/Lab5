import utils.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите путь к файлу с коллекцией: ");
        String filePath = scanner.nextLine();
        FileManager fileManager = new FileManager(filePath);
        fileManager.parseFromXml();
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.startInteractiveMode();
    }
}
