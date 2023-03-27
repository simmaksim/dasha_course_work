package command.impl;

import command.CommandException;
import command.EntityBuilder;
import command.PagePath;
import dbbinding.impl.ClientBinding;
import entity.Client;
import repository.RepositoryException;
import repository.impl.SqlRepository;
import validation.impl.ClientValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AddClientCommand implements CommandWithValidation {
    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String params = "?command=to_clients_list";
            response.sendRedirect(PagePath.SERVLET + params);
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public void onValidationError(HttpServletRequest request, HttpServletResponse response, String feedback) throws CommandException {
        try {
            request.setAttribute("feedback", feedback);
            request.getRequestDispatcher(PagePath.SERVLET + "?command=to_add_client").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Optional<Client> optionalClient = EntityBuilder.INSTANCE.buildClient(request);
        ClientValidator validator = new ClientValidator();

        if (!optionalClient.isPresent())
        {
            try {
                response.sendError(400);
            } catch (IOException e) {
                throw new CommandException(e);
            }
            return;
        }

        Client client = optionalClient.get();

        if (!validator.isValid(client)) {
            onValidationError(request, response, validator.getValidationFeedback());
            return;
        }

        try {
            new SqlRepository<Client>(new ClientBinding()).addEntity(client);
        } catch (RepositoryException e) {
            onValidationError(request, response, "Ошибка добавления клиента в базу данных");
            return;
        }

        onSuccess(request, response);
    }

    @Override
    public String getCommandName() {
        return "add_client";
    }
}
