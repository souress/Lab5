package commands;

import commands.utils.CommandReceiver;

public class ExecuteScriptCommand extends AbstractCommand {
    private static String path;
    private final CommandReceiver commandReceiver;

    public ExecuteScriptCommand(CommandReceiver commandReceiver) {
        super("execute_script file_name", "исполнить скрипт из указанного файла");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) throws StackOverflowError {
        try {
            if (args.length == 2) {
                path = args[1];
                commandReceiver.executeScript(args[1]);
            } else System.out.println("Некорректное количество аргументов. Для справки напишите help.");
        } catch (StackOverflowError ex) {
            System.out.println("Ошибка! Обнаружен выход за пределы стека.");
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
