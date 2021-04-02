package commands;

public class PrintFieldDescendingHeightCommand extends AbstractCommand {

    public PrintFieldDescendingHeightCommand() {
        super("print_field_descending_height", "вывести значения поля height всех элементов в порядке " +
                                                                                                            "убывания");
    }

    @Override
    public void execute(String argument) {
        System.out.println("PRINT_FIELD_DESCENDING_HEIGHT_EXECUTED");
    }
}
