package commands;

public class InfoCommand extends AbstractCommand {

    public InfoCommand() {
        super("info", "вывести информацию о коллекции");
    }

    @Override
    public void execute(String argument) {
        System.out.println("INFO_COMMAND_EXECUTED");
    }
}
