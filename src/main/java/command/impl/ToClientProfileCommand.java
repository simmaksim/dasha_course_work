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

public class ToClientProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String stringId = request.getParameter(ParameterName.CLIENT_ID);
        int id = Integer.parseInt(stringId);

        try {
            Client client = new SqlRepository<>(new ClientBinding()).getEntityById(id);
            request.setAttribute(ParameterName.CLIENT, client);
            request.getRequestDispatcher(PagePath.CLIENT_PROFILE).forward(request, response);
        } catch (RepositoryException | ServletException | IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "to_client_profile";
    }
}
