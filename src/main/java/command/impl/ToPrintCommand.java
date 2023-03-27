package command.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import command.Command;
import command.CommandException;
import command.ParameterName;
import dbbinding.impl.OrderBinding;
import entity.Order;
import repository.RepositoryException;
import repository.impl.SqlRepository;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToPrintCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            int id = Integer.parseInt(request.getParameter(ParameterName.ORDER_ID));
            Order client = new SqlRepository<Order>(new OrderBinding()).getEntityById(id);

            Document document = new Document();
            response.setContentType("application/pdf");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=order.pdf");
            PdfWriter.getInstance(document, response.getOutputStream());
            OrderService.INSTANCE.printPdf(document, client);
        } catch (RepositoryException | DocumentException | IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "to_print";
    }
}
