package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToAddClientCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            request.getRequestDispatcher(PagePath.ADD_CLIENT).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "to_add_client";
    }
}
