package commands;

public class RemoveLowerCommand extends AbstractCommand {

    public RemoveLowerCommand() {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
    }

    @Override
    public void execute(String argument) {
        System.out.println("REMOVE_LOWER_COMMAND_EXECUTED");
    }
}
