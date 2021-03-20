package commands;

public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", "вывести справку по доступным командам");
    }

    @Override
    public boolean execute(String argument) {
        if (argument.isEmpty())
        return false;
    }
}
