package commands;

public class FilterStartsWithNameCommand extends AbstractCommand {

    public FilterStartsWithNameCommand() {
        super("filter_starts_with_name name", "вывести элементы, значение поля name которых начинается" +
                                                                                               " с заданной подстроки");
    }

    @Override
    public void execute(String argument) {
        System.out.println("FILTER_STARTS_WITH_NAME_COMMAND_EXECUTED");
    }
}
