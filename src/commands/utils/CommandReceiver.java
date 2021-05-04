package commands.utils;

import commands.*;
import data.*;
import utils.*;
import utils.readers.*;
import utils.readers.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.*;

/**
 * Ресивер(получатель), описывает основную логику команд, при надобности делегирует ее менеджеру коллекции.
 */
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
        class ScriptPersonCreator {
            public Person createScriptPerson(Scanner scanner) {
                String name = StringReader.readFromScript(scanner, false);
                Long coordinateX = RefLongReader.readFromScript(scanner, 51, "MAX");
                double coordinateY = PrimitiveDoubleReader.readFromScript(scanner);
                double height = PrimitiveDoubleReader.readFromScript(scanner, 0, "MIN");
                String passportID = StringReader.readFromScript(scanner, true);
                Color hairColor = ColorReader.readFromScript(scanner, false);
                Country nationality = CountryReader.readFromScript(scanner, true);
                long locationX = PrimitiveLongReader.readFromScript(scanner);
                float locationY = PrimitiveFloatReader.readFromScript(scanner);
                Long locationZ = RefLongReader.readFromScript(scanner);

                return new Person(name, new Coordinates(coordinateX, coordinateY), height, passportID, hairColor, nationality,
                        new Location(locationX, locationY, locationZ));
            }
        }

        if (args.length != 2) System.out.println("Некорректное количество аргументов. Для справки напишите help.");
        else {
            Scanner scanner = new Scanner(new FileReader(args[1]));
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (command.matches("add|update|add_if_max|remove_greater|remove_lower")) {
                    Person person;
                    ScriptPersonCreator scriptPersonCreator = new ScriptPersonCreator();
                    switch (command) {
                        case "add":
                            try {
                                person = scriptPersonCreator.createScriptPerson(scanner);
                                CollectionManager.add(person);
                            } catch (NullPointerException | IllegalArgumentException exception) {
                                System.out.println("Параметры команды add не прошли валидацию. Команда пропущена.");
                                continue;
                            }
                            break;
                        case "update":
                            try {
                                String id = scanner.nextLine();
                                if (!CollectionManager.checkExist(Integer.parseInt(id)))
                                    throw new IllegalStateException();
                                person = scriptPersonCreator.createScriptPerson(scanner);
                                CollectionManager.update(person, Integer.parseInt(id));
                                System.out.println("Элемент обновлен.");
                            } catch (NullPointerException | IllegalArgumentException exception) {
                                System.out.println("Параметры команды update не прошли валидацию. Команда пропущена.");
                                continue;
                            } catch (IllegalStateException exception) {
                                System.out.println("id в команде update не найден!");
                            }
                            break;
                        case "add_if_max":
                            try {
                                person = scriptPersonCreator.createScriptPerson(scanner);
                                CollectionManager.addIfMax(person);
                            } catch (NullPointerException | IllegalArgumentException exception) {
                                System.out.println("Параметры команды add_if_max не прошли валидацию. Команда пропущена.");
                                continue;
                            }
                            break;
                        case "remove_greater":
                            try {
                                person = scriptPersonCreator.createScriptPerson(scanner);
                                CollectionManager.removeGreater(person);
                            } catch (NullPointerException | IllegalArgumentException exception) {
                                System.out.println("Параметры команды remove_greater не прошли валидацию. Команда пропущена.");
                                continue;
                            }
                            break;
                        case "remove_lower":
                            try {
                                person = scriptPersonCreator.createScriptPerson(scanner);
                                CollectionManager.removeLower(person);
                            } catch (NullPointerException | IllegalArgumentException exception) {
                                System.out.println("Параметры команды remove_lower не прошли валидацию. Команда пропущена.");
                                continue;
                            }
                            break;
                    }
                } else if (command.matches("execute_script")) {
                    pathSet.add(args[1]);
                    dante:
                    {
                        String line = scanner.nextLine();
                        try {
                            for (String path : pathSet) {
                                if (line.matches(path))
                                    throw new StackOverflowError();
                            }
                        } catch (StackOverflowError error) {
                            System.out.println("Обнаружен рекурсивный вызов скрипта! Попытка переполнения стека будет пресечена.");
                        } finally {
                            for (String path : pathSet) {
                                if (line.contains(path))
                                    if (scanner.hasNextLine())
                                        line = scanner.nextLine();
                                    else break dante;
                            }
                            commandInvoker.executeCommand(command.split(" "));
                        }
                    }
                } else if (commandInvoker.getCommandMap().containsKey(command))
                    commandInvoker.executeCommand(command.split(" "));
            }
        }
    }

//    public void execute_script_from_interactive_mode(String[] args) throws FileNotFoundException {
//        if (args.length != 2) System.out.println("Некорректное количество аргументов. Для справки напишите help.");
//        else {
//            Scanner scanner = new Scanner(new FileReader(args[1]));
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                if (line.split(" ")[0].matches("add|update|add_if_max|remove_greater|remove_lower")) {
//                    Person person;
//                    ArrayList<String> personFields = new ArrayList<>(Arrays.asList(line.split(" ")));
//                    personFields.remove(0);
//                    for (String parameter : personFields) {
//                        if (parameter.equals("null")) personFields.set(personFields.indexOf(parameter), null);
//                    }
//                    switch (line.split(" ")[0]) {
//                        case "add":
//                            try {
//                                person = PersonCreator.createScriptPerson(personFields);
//                                CollectionManager.add(person);
//                            } catch (NullPointerException exception) {
//                                System.out.println("Параметры команды add не прошли валидацию. Команда пропущена.");
//                                continue;
//                            }
//                            break;
//                        case "update":
//                            try {
//                                personFields.remove(0);
//                                person = PersonCreator.createScriptPerson(personFields);
//                                CollectionManager.update(person, Integer.parseInt(line.split(" ")[1]));
//                                System.out.println("Элемент обновлен.");
//                            } catch (NullPointerException exception) {
//                                System.out.println("Параметры команды update не прошли валидацию. Команда пропущена.");
//                                continue;
//                            } catch (IllegalArgumentException exception) {
//                                System.out.println("id в команде update не найден!");
//                            }
//                            break;
//                        case "add_if_max":
//                            try {
//                                person = PersonCreator.createScriptPerson(personFields);
//                                CollectionManager.addIfMax(person);
//                            } catch (NullPointerException exception) {
//                                System.out.println("Параметры команды add_if_max не прошли валидацию. Команда пропущена.");
//                                continue;
//                            }
//                            break;
//                        case "remove_greater":
//                            try {
//                                person = PersonCreator.createScriptPerson(personFields);
//                                CollectionManager.removeGreater(person);
//                            } catch (NullPointerException exception) {
//                                System.out.println("Параметры команды remove_greater не прошли валидацию. Команда пропущена.");
//                                continue;
//                            }
//                            break;
//                        case "remove_lower":
//                            try {
//                                person = PersonCreator.createScriptPerson(personFields);
//                                CollectionManager.removeLower(person);
//                            } catch (NullPointerException exception) {
//                                System.out.println("Параметры команды remove_lower не прошли валидацию. Команда пропущена.");
//                                continue;
//                            }
//                            break;
//                    }
//                } else if(line.split(" ")[0].equals("execute_script")) {
//                    pathSet.add(args[1]);
//                    dante:
//                    {
//                        try {
//                            for (String path : pathSet) {
//                                if (line.split(" ")[1].equals(path))
//                                    throw new StackOverflowError();
//                            }
//                        } catch (StackOverflowError error) {
//                            System.out.println("Обнаружен рекурсивный вызов скрипта! Попытка переполнения стека будет пресечена.");
//                        } finally {
//                            for (String path : pathSet) {
//                                if (line.contains(path))
//                                    if (scanner.hasNextLine())
//                                        line = scanner.nextLine();
//                                    else break dante;
//                            }
//                            String[] command = line.split(" ");
//                            commandInvoker.executeCommand(command);
//                        }
//                    }
//                } else commandInvoker.executeCommand(line.split(" "));
//            }
//        }
//    }

    public void removeByID(String id) {
        Integer personId;
        try {
            personId = Integer.parseInt(id);
            if (CollectionManager.checkExist(personId)) {
                CollectionManager.removeByID(personId);
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
