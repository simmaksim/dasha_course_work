package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;
import dbbinding.impl.OrderBinding;
import entity.Entity;
import entity.Order;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int order_id = Integer.parseInt(request.getParameter(ParameterName.ORDER_ID));

        try {
            SqlRepository<Order> repository = new SqlRepository<Order>(new OrderBinding());
            Order order = repository.getEntityById(order_id);
            repository.removeEntity(order);

            response.sendRedirect(PagePath.SERVLET + "?command=to_orders_list");
        } catch (RepositoryException | IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "remove_order";
    }
}
