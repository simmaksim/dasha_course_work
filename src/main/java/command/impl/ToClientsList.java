package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;
import dbbinding.impl.ClientBinding;
import entity.Client;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToClientsList implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            request.setAttribute(ParameterName.CLIENTS_LIST,
                    new SqlRepository<Client>(new ClientBinding()).getAllEntities());
            request.getRequestDispatcher(PagePath.CLIENTS_LIST).forward(request, response);
        } catch (ServletException | IOException | RepositoryException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "to_clients_list";
    }
}
