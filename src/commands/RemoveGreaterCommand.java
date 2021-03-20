package commands;

public class RemoveGreaterCommand extends AbstractCommand {

    public RemoveGreaterCommand() {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
    }

    @Override
    public boolean execute(String argument) {

    }
}
