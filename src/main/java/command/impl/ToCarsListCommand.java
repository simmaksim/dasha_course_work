package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;
import dbbinding.impl.CarBinding;
import dbbinding.impl.CarMakeBindingCommand;
import entity.Car;
import entity.CarMake;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToCarsListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            request.setAttribute(ParameterName.CARS_LIST,
                    new SqlRepository<Car>(new CarBinding()).getAllEntities());
            request.setAttribute(ParameterName.CAR_MAKES,
                    new SqlRepository<CarMake>(new CarMakeBindingCommand()).getAllEntities());

            request.getRequestDispatcher(PagePath.CARS_LIST).forward(request, response);
        } catch (RepositoryException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "to_cars_list";
    }
}
