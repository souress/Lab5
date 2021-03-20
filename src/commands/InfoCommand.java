package commands;

public class InfoCommand extends AbstractCommand {

    public InfoCommand() {
        super("info", "вывести информацию о коллекции");
    }

    @Override
    public boolean execute(String argument) {

        return false;
    }
}
