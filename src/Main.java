import utils.*;

/**
 * Main класс приложения
 */
public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager(args[0]);
        fileManager.parseFromXml();
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.startInteractiveMode();
    }
}
