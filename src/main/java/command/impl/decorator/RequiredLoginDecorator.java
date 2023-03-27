package command.impl.decorator;

import command.Command;
import command.CommandException;
import entity.Employee;
import service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class RequiredLoginDecorator extends CommandDecorator {
    public RequiredLoginDecorator(Command command) {
        super(command);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String employeeLogin = (String) request.getSession().getAttribute("login");
        Optional<Employee> employeeOptional = EmployeeService.INSTANCE.getByLogin(employeeLogin);

        if (!employeeOptional.isPresent()) {
            try {
                response.sendError(403); // forbidden
            } catch (IOException e) {
                throw new CommandException(e);
            }
            return;
        }

        super.execute(request, response);
    }
}
