import data.Person;
import org.xml.sax.SAXException;
import utility.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        CollectionManager collectionManager = new CollectionManager(console);
        FileManager fileManager = new FileManager(collectionManager);
        HashSet<Person> personHashSet = new HashSet<>();

        try {
            personHashSet = fileManager.parseFromXml();
        } catch (IOException | ParserConfigurationException | SAXException exception) {
            exception.printStackTrace();
        }

        for (Person person : personHashSet) System.out.println(person.toString());

        try {
            fileManager.parseToXml("C:\\Users\\Кирилл\\IdeaProjects\\Lab5\\src\\test\\output.xml", personHashSet);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
