package commands;

import commands.utils.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ExecuteScriptCommand extends AbstractCommand {
    private static String path;
    private final CommandReceiver commandReceiver;
    private final CommandInvoker commandInvoker;
    private static ArrayList<String> calls;


    public ExecuteScriptCommand(CommandReceiver commandReceiver, CommandInvoker commandInvoker) {
        super("execute_script file_name", "исполнить скрипт из указанного файла");
        this.commandReceiver = commandReceiver;
        calls = new ArrayList<>();
        this.commandInvoker = commandInvoker;
    }

    @Override
    public void execute(String[] args) throws StackOverflowError, FileNotFoundException {
        String[] command;
        boolean exit = false;
        if (args.length != 2) System.out.println("Некорректное количество аргументов. Для справки напишите help.");
        else {
            path = args[1];
            for (String com : calls) {
                if (com.equals(args[1])) {
                    exit = true;
                    break;
                }
            }
            if (exit) {
                System.err.println("Предупреждение! Возможна бесконечная рекурсия: ");
            } else {
                Scanner scanner = new Scanner(new FileReader(args[1]));
                String nextLine;
                while (scanner.hasNextLine()) {
                    nextLine = scanner.nextLine();
                    command = nextLine.split(" ");
                    calls.add(args[0]);
                    commandInvoker.executeCommand(command);
                }
                commandReceiver.executeScript(path);
            }
        }
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }

    public static String getPath() {
        return path;
    }
}
