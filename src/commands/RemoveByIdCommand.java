package commands;

public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand() {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
    }

    @Override
    public void execute(String argument) {
        System.out.println("REMOVE_BY_ID_COMMAND_EXECUTED");
    }
}
