package commands;

public class AddIfMaxCommand extends AbstractCommand {

    public AddIfMaxCommand() {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает " +
                                                                        "значение наибольшего элемента этой коллекции");
    }

    @Override
    public boolean execute(String argument) {

    }
}
