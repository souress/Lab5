package commands;

public class UpdateIdCommand extends AbstractCommand {

    public UpdateIdCommand() {
        super("update <id> {element}", "обновить значение элемента коллекции по id");
    }

    @Override
    public void execute(String argument) {
        System.out.println("UPDATE_ID_COMMAND_EXECUTED");
    }
}
