package service;

import com.ibm.icu.text.Transliterator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import dbbinding.impl.CarBinding;
import dbbinding.impl.ClientBinding;
import entity.Car;
import entity.Client;
import entity.Order;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import java.io.IOException;

public enum OrderService {
    INSTANCE;

    public void printPdf(Document document, Order order) throws IOException, DocumentException, RepositoryException {
        Client client = new SqlRepository<Client>(new ClientBinding()).getEntityById(order.getClientId());
        Car car = new SqlRepository<Car>(new CarBinding()).getEntityById(order.getCarId());
        BaseFont bf=BaseFont.createFont(BaseFont.TIMES_ROMAN, "UTF-8", BaseFont.EMBEDDED);
        Transliterator trans = Transliterator.getInstance("Cyrillic-Latin");
        Font font=new Font(bf,16, Font.NORMAL);
        document.open();
        document.add(new Paragraph("Order", font));
        document.add(new Paragraph("Name:" + trans.transliterate(client.getName()), font));
        document.add(new Paragraph("Surname:" + trans.transliterate(client.getSurname()), font));
        document.add(new Paragraph("Passport:" + trans.transliterate(client.getPassportId()), font));
        document.add(new Paragraph("Model:" + trans.transliterate(car.getModel()), font));
        document.add(new Paragraph("Start date:" + order.getStartDate().toString(), font));
        document.add(new Paragraph("End date:" + order.getEndDate().toString(), font));

        document.close();
    }
}
