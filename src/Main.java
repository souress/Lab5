import utils.*;

/**
 * Main класс приложения
 * @author Клименко Кирилл P3114
 */
public class Main {
    public static void main(String[] args) {
        try {
            if (args[1] == null) throw new IllegalArgumentException();
            FileManager fileManager = new FileManager(args[0]);
            fileManager.parseFromXml();
            ConsoleManager consoleManager = new ConsoleManager();
            consoleManager.startInteractiveMode();
        } catch (IllegalArgumentException exception) {
            System.out.println("Укажите путь к файлу через аргумент командной строки.");
        }
    }
}
