package command.impl;

import command.CommandException;
import command.EntityBuilder;
import command.PagePath;
import command.impl.CommandWithValidation;
import dbbinding.impl.OrderBinding;
import entity.Order;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

public class AddOrderCommand implements CommandWithValidation {
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
            request.getRequestDispatcher(PagePath.SERVLET + "?command=to_add_order").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Optional<Order> optionalOrder = EntityBuilder.INSTANCE.buildOrder(request);

        if (!optionalOrder.isPresent())
        {
            try {
                response.sendError(400);
            } catch (IOException e) {
                throw new CommandException(e);
            }
            return;
        }

        Order order = optionalOrder.get();

        try {
            new SqlRepository<Order>(new OrderBinding()).addEntity(order);
        } catch (RepositoryException e) {
            onValidationError(request, response, "Ошибка добавления заказа в базу данных");
            return;
        }

        onSuccess(request, response);
    }

    @Override
    public String getCommandName() {
        return "add_order";
    }
}
