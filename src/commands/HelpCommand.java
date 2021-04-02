package commands;

public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", "вывести справку по доступным командам");
    }

    @Override
    public void execute(String argument) {
        System.out.println("HELP_COMMAND_EXECUTED");
    }
}
