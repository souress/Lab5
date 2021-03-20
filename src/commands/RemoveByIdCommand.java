package commands;

public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand() {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
    }

    @Override
    public boolean execute(String argument) {

    }
}
