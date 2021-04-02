package commands;

public class ShowCommand extends AbstractCommand {

    public ShowCommand() {
        super("show", "вывести все элементы коллекции");
    }

    @Override
    public void execute(String argument) {
        System.out.println("SHOW_COMMAND_EXECUTED");
    }
}
