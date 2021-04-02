package commands;

public class ExecuteScriptCommand extends AbstractCommand {

    public ExecuteScriptCommand() {
        super("execute_script file_name", "исполнить скрипт из указанного файла");
    }

    @Override
    public void execute(String argument) {
        System.out.println("EXECUTE_SCRIPT_COMMAND_EXECUTED");
    }
}
