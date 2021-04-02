package commands;

public class PrintFieldDescendingPassportIdCommand extends AbstractCommand {

    public PrintFieldDescendingPassportIdCommand() {
        super("print_field_descending_passport_id", "вывести значения поля passportID всех элементов в" +
                                                                                                    " порядке убывания");
    }

    @Override
    public void execute(String argument) {
        System.out.println("PRINT_FIELD_DESCENDING_PASSPORT_ID_COMMAND_EXECUTED");
    }
}
