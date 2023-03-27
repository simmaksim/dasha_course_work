package command.impl.decorator;

import command.Command;
import command.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CommandDecorator implements Command {
    private Command command;

    public CommandDecorator(Command command) {
        this.command = command;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        command.execute(request, response);
    }

    @Override
    public String getCommandName() {
        return command.getCommandName();
    }
}
