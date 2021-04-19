package commands.utils;

import commands.AbstractCommand;

import java.util.HashMap;

public class CommandInvoker {
    private final HashMap<String, AbstractCommand> commandMap = new HashMap<>();

    public void register(String commandName, AbstractCommand command) {
        commandMap.put(commandName, command);
    }

    public void executeCommand(String[] commandName) {
        try {
            if (commandName.length > 0) {
                AbstractCommand command = commandMap.get(commandName[0]);
                command.execute(commandName);
            } else System.out.println("Вы не ввели команду.");
        } catch (IllegalStateException | NullPointerException exception) {
            System.out.println("Не существует команды " + commandName[0] + ". Для справки используйте – help");
        }
    }

    public  HashMap<String, AbstractCommand> getCommandMap() {
        return commandMap;
    }
}
