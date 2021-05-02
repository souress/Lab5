package commands.utils;

import commands.*;
import utils.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.*;

public class CommandReceiver {
    private final CommandInvoker commandInvoker;
    private final LinkedHashSet<String> pathSet;

    public CommandReceiver(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
        pathSet = new LinkedHashSet<>();
    }

    public void add() {
        CollectionManager.add(PersonCreator.createPerson());
        System.out.println("Элемент добавлен в коллекцию");
    }

    public void help() {
        for (AbstractCommand command : commandInvoker.getCommandMap().values()) {
            System.out.println(command.writeInfo());
        }
    }

    public void info() {
        CollectionManager.getInfo();
    }

    public void show() {
        CollectionManager.show();
    }

    public void clear() {
        CollectionManager.clear();
        System.out.println("Коллекция очищена");
    }

    public void execute_script(String[] args) throws FileNotFoundException {
        if (args.length != 2) System.out.println("Некорректное количество аргументов. Для справки напишите help.");
        else {
            pathSet.add(args[1]);
            Scanner scanner = new Scanner(new FileReader(args[1]));
            dante:
            {
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    try {
                        for (String path : pathSet) {
                            if (nextLine.contains(path))
                                throw new StackOverflowError();
                        }
                    } catch (StackOverflowError error) {
                        System.out.println("Обнаружен рекурсивный вызов скрипта! Попытка переполнения стека будет пресечена.");
                    } finally {
                        for (String path : pathSet) {
                            if (nextLine.contains(path))
                                if (scanner.hasNextLine())
                                    nextLine = scanner.nextLine();
                                else break dante;
                        }
                        String[] command = nextLine.split(" ");
                        commandInvoker.executeCommand(command);
                    }
                }
            }
        }
    }

    public void remove_by_id(String id) {
        Integer personId;
        try {
            personId = Integer.parseInt(id);
            if (CollectionManager.checkExist(personId)) {
                CollectionManager.remove_by_id(personId);
                System.out.println("Элемент с ID " + personId + " успешно удален из коллекции.");
            } else System.out.println("Элемента с таким ID нет в коллекции.");
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }

    public void update(String id) {
        try {
            Integer personId = Integer.parseInt(id);
            if (CollectionManager.checkExist(personId)) {
                CollectionManager.update(PersonCreator.createPerson(), personId);
                System.out.println("Элемент обновлен.");
            }
            else System.out.println("Элемента с таким ID нет в коллекции.");
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }

    public void removeGreater() {
        CollectionManager.removeGreater(PersonCreator.createPerson());
    }

    public void removeLower() {
        CollectionManager.removeLower(PersonCreator.createPerson());
    }

    public void addIfMax() {
        CollectionManager.addIfMax(PersonCreator.createPerson());
    }

    public void printFieldDescendingHeight() {
        CollectionManager.printFieldDescendingHeight();
    }

    public void printFieldDescendingPassportID() {
        CollectionManager.printFieldDescendingPassportID();
    }

    public void filterStartsWithName(String name){
        CollectionManager.filterStartsWithName(name);
    }

    public void save() throws ParserConfigurationException, TransformerException, IOException {
        FileManager.parseToXml(CollectionManager.getHashSet());
    }

    public void exit() {
        System.out.println("Выход...");
        System.exit(0);
    }
}
