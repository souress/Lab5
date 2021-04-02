package commands;

public class RemoveGreaterCommand extends AbstractCommand {

    public RemoveGreaterCommand() {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
    }

    @Override
    public void execute(String argument) {
        System.out.println("REMOVE_GREATER_COMMAND_EXECUTED");
    }
}
