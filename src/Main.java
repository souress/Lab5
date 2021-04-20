import data.Person;
import utils.*;

import java.io.FileNotFoundException;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileManager.parseFromXml();
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.startInteractiveMode();
    }
}
