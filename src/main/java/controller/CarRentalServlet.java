package controller;

import command.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(PagePath.SERVLET)
public class CarRentalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String commandName = request.getParameter(ParameterName.COMMAND);

        try {
            Optional<Command> optionalCommand = CommandProvider.INSTANCE.findCommand(commandName);
            Command command = optionalCommand.orElseThrow(CommandException::new);

            command.execute(request, response);
        } catch (CommandException e) {
            throw new ServletException(e);
        }
    }
}