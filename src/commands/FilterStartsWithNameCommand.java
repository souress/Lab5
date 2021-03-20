package commands;

public class FilterStartsWithNameCommand extends AbstractCommand {

    public FilterStartsWithNameCommand() {
        super("filter_starts_with_name name", "вывести элементы, значение поля name которых начинается" +
                                                                                               " с заданной подстроки");
    }

    @Override
    public boolean execute(String argument) {

        return false;
    }
}
