package commands;

public class SaveCommand extends AbstractCommand {

    public SaveCommand() {
        super("save", "сохранить коллекцию в файл");
    }

    @Override
    public void execute(String argument) {
        System.out.println("SAVE_COMMAND_EXECUTED");
    }
}
