package commands;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String argument) {
        System.out.println("EXIT_COMMAND_EXECUTED");
    }
}
