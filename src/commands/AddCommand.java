package commands;

public class AddCommand extends AbstractCommand {

    public AddCommand() {
        super("add {element}", "добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(String argument) {
        System.out.println("ADD_COMMAND_EXECUTED");
    }
}
