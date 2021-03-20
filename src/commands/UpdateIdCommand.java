package commands;

public class UpdateIdCommand extends AbstractCommand {

    public UpdateIdCommand() {
        super("update <id> {element}", "обновить значение элемента коллекции по id");
    }

    @Override
    public boolean execute(String argument) {

    }
}
