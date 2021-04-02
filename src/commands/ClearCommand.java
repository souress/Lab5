package commands;

public class ClearCommand extends AbstractCommand {

    public ClearCommand() {
        super("clear", "очистить коллекцию");
    }

    @Override
    public void execute(String argument) {
        System.out.println("CLEAR_COMMAND_EXECUTED");
    }
}
