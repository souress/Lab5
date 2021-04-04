package utility;

import test.Person;

import java.time.ZonedDateTime;
import java.util.HashSet;

public class CollectionManager {
    private final HashSet<Person> collection;
    private final Console console;
    private final ZonedDateTime time;

    public CollectionManager(Console console) {
        collection = new HashSet<>();
        time = ZonedDateTime.now();
        this.console = console;
    }

    public String getFilePath() {
        System.out.println("Enter path to the xml-file: ");
        return console.readLine();
    }
}
