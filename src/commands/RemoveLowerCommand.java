package commands;

public class RemoveLowerCommand extends AbstractCommand {

    public RemoveLowerCommand() {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
    }

    @Override
    public boolean execute(String argument) {

    }
}
