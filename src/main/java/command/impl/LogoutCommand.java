package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            request.getSession().invalidate();
            String param = "?command=to_login";
            response.sendRedirect(PagePath.SERVLET + param);
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "logout";
    }
}
