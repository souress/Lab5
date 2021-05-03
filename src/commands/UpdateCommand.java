package commands;

import commands.utils.CommandReceiver;

/**
 * Класс команды, обновляющей элемент по id.
 */
public class UpdateCommand extends AbstractCommand {
    private final CommandReceiver commandReceiver;

    public UpdateCommand(CommandReceiver commandReceiver) {
        super("update <id> {element}", "обновить значение элемента коллекции по id");
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) commandReceiver.update(args[1]);
        else System.out.println("Некорректное количество аргументов. Для справки напишите help.");
    }

    @Override
    public String writeInfo() {
        return getName() + " - " + getDescription();
    }
}
