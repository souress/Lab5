import utility.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        CollectionManager collectionManager = new CollectionManager(console);
        FileManager fileManager = new FileManager(collectionManager);
    }
}
