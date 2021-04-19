package commands.utils;

import commands.AbstractCommand;
import commands.ExecuteScriptCommand;
import data.Person;
import utils.CollectionManager;
import utils.FileManager;
import utils.PersonCreator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CommandReceiver {
    private final CommandInvoker commandInvoker;

    public CommandReceiver(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
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

    public void executeScript(String path) {
        String line;
        String command;
        ArrayList<String> args = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(path)), StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(" ")[0].matches("add|update|add_if_max|remove_greater")) {
                    command = line;
                    for (int i = 0; i < 8; i++) {
                        if (line != null) {
                            line = bufferedReader.readLine();
                            args.add(line);
                        } else
                            System.out.println("Не хватает параметров для создания объекта."); break;
                    }
                    Person person = PersonCreator.createPersonInScript(args);
                    switch (command.split(" ")[0]) {
                        case "add":
                            CollectionManager.add(person);
                            break;
                        case "update":
                            CollectionManager.update(person, Integer.parseInt(command.split(" ")[1]));
                            break;
                        case "remove_greater":
                            CollectionManager.removeGreater(person);
                            break;
                        case "remove_lower":
                            CollectionManager.removeLower(person);
                            break;
                        case "add_if_max":
                            CollectionManager.addIfMax(person);
                            break;
                    }
                } else
                    if (line.split(" ")[0].equals("execute_script") && line.split(" ")[1].equals(ExecuteScriptCommand.getPath()))
                        System.out.println("Пресечена попытка рекурсивного вызова скрипта.");
                    else commandInvoker.executeCommand(line.split(" "));
            }
        } catch (IOException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
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
