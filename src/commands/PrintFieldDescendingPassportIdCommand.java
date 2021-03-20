package commands;

public class PrintFieldDescendingPassportIdCommand extends AbstractCommand {

    public PrintFieldDescendingPassportIdCommand() {
        super("print_field_descending_passport_id", "вывести значения поля passportID всех элементов в" +
                                                                                                    " порядке убывания");
    }

    @Override
    public boolean execute(String argument) {

    }
}
