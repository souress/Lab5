import data.Person;
import utils.*;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        FileManager.parseFromXml();
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.startInteractiveMode();
    }
}
