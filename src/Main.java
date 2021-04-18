import data.Person;
import org.xml.sax.SAXException;
import utility.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;

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

        List<Person> personList = new ArrayList<>(personHashSet);
        personList.sort(Person::compareTo);

        System.out.println(personList);

        try {
            fileManager.parseToXml("C:\\Users\\Кирилл\\IdeaProjects\\Lab5\\src\\test\\output.xml", personList);
        } catch (IOException | TransformerException | ParserConfigurationException exception) {
            exception.printStackTrace();
        }
    }
}
