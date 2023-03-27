package command.impl;

import command.CommandException;
import command.EntityBuilder;
import command.PagePath;
import entity.Employee;
import service.EmployeeService;
import validation.impl.EmployeeValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements CommandWithValidation {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Employee employee = EntityBuilder.INSTANCE.buildEmployee(request).orElseThrow(CommandException::new);
        EmployeeValidator validator = new EmployeeValidator();

        if (!validator.isValid(employee)) {
            onValidationError(request, response, validator.getValidationFeedback());
            return;
        }

        if (!EmployeeService.INSTANCE.checkCredentials(employee)) {
            onValidationError(request, response, "Неверный логин или пароль");
            return;
        }

        request.getSession().setAttribute("login", employee.getLogin());
        onSuccess(request, response);
    }

    @Override
    public String getCommandName() {
        return "login";
    }

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
            request.getRequestDispatcher(PagePath.SERVLET + "?command=to_login").forward(request, response);
        } catch (IOException | ServletException e) {
            throw new CommandException(e);
        }
    }
}
