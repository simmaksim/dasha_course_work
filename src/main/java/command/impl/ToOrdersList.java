package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;
import dbbinding.impl.CarBinding;
import dbbinding.impl.ClientBinding;
import dbbinding.impl.OrderBinding;
import entity.Car;
import entity.Client;
import entity.Order;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToOrdersList implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            request.setAttribute(ParameterName.CLIENTS_LIST,
                    new SqlRepository<Client>(new ClientBinding()).getAllEntities());
            request.setAttribute(ParameterName.ORDERS_LIST,
                    new SqlRepository<Order>(new OrderBinding()).getAllEntities());
            request.setAttribute(ParameterName.CARS_LIST,
                    new SqlRepository<Car>(new CarBinding()).getAllEntities());
            request.getRequestDispatcher(PagePath.ORDERS_LIST).forward(request, response);
        } catch (ServletException | IOException | RepositoryException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "to_orders_list";
    }
}
