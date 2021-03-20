package commands;

public class AddCommand extends AbstractCommand {

    public AddCommand() {
        super("add {element}", "добавить новый элемент в коллекцию");
    }

    @Override
    public boolean execute(String argument) {

    }
}
