package utility;

import commands.*;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<AbstractCommand> commands = new ArrayList<>();
    private HelpCommand helpCommand;
    private AddCommand addCommand;
    private AddIfMaxCommand addIfMaxCommand;
    private ClearCommand clearCommand;
    private ExecuteScriptCommand executeScriptCommand;
    private ExitCommand exitCommand;
    private FilterStartsWithNameCommand filterStartsWithNameCommand;
    private InfoCommand infoCommand;
    private PrintFieldDescendingHeightCommand printFieldDescendingHeightCommand;
    private PrintFieldDescendingPassportIdCommand printFieldDescendingPassportIdCommand;
    private RemoveByIdCommand removeByIdCommand;
    private RemoveGreaterCommand removeGreaterCommand;
    private RemoveLowerCommand removeLowerCommand;
    private SaveCommand saveCommand;
    private ShowCommand showCommand;
    private UpdateIdCommand updateIdCommand;

    public CommandManager(HelpCommand helpCommand, AddCommand addCommand, AddIfMaxCommand addIfMaxCommand, ClearCommand clearCommand,
                          ExecuteScriptCommand executeScriptCommand, ExitCommand exitCommand, RemoveByIdCommand removeByIdCommand,
                          FilterStartsWithNameCommand filterStartsWithNameCommand, InfoCommand infoCommand,
                          PrintFieldDescendingHeightCommand printFieldDescendingHeightCommand,
                          PrintFieldDescendingPassportIdCommand printFieldDescendingPassportIdCommand, RemoveGreaterCommand removeGreaterCommand,
                          RemoveLowerCommand removeLowerCommand, SaveCommand saveCommand, ShowCommand showCommand, UpdateIdCommand updateIdCommand) {
        this.helpCommand = helpCommand;
        this.addCommand = addCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.clearCommand = clearCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.removeByIdCommand= removeByIdCommand;
        this.filterStartsWithNameCommand = filterStartsWithNameCommand;
        this.infoCommand = infoCommand;
        this.printFieldDescendingHeightCommand = printFieldDescendingHeightCommand;
        this.printFieldDescendingPassportIdCommand = printFieldDescendingPassportIdCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.saveCommand = saveCommand;
        this.showCommand = showCommand;
        this.updateIdCommand = updateIdCommand;

        commands.add(helpCommand);
        commands.add(addCommand);
        commands.add(addIfMaxCommand);
        commands.add(clearCommand);
        commands.add(executeScriptCommand);
        commands.add(exitCommand);
        commands.add(removeByIdCommand);
        commands.add(filterStartsWithNameCommand);
        commands.add(infoCommand);
        commands.add(printFieldDescendingHeightCommand);
        commands.add(printFieldDescendingPassportIdCommand);
        commands.add(removeGreaterCommand);
        commands.add(removeLowerCommand);
        commands.add(saveCommand);
        commands.add(showCommand);
        commands.add(updateIdCommand);
    }

    public List<AbstractCommand> getCommands(){
        return commands;
    }
}
