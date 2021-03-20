package commands;

public class ExecuteScriptCommand extends AbstractCommand {

    public ExecuteScriptCommand() {
        super("execute_script file_name", "исполнить скрипт из указанного файла");
    }

    @Override
    public boolean execute(String argument) {

        return false;
    }
}
