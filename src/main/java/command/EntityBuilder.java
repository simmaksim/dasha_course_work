package command;

import entity.Client;
import entity.Employee;
import entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public enum EntityBuilder {
    INSTANCE;

    public Optional<Employee> buildEmployee(HttpServletRequest request) {
        Employee employee = new Employee();

        employee.setLogin(request.getParameter(ParameterName.LOGIN));
        employee.setPassword(request.getParameter(ParameterName.PASSWORD));

        return Optional.of(employee);
    }

    public Optional<Client> buildClient(HttpServletRequest request) {
        Client client = new Client();

        try {
            client.setName(request.getParameter(ParameterName.NAME));
            client.setSurname(request.getParameter(ParameterName.SURNAME));
            client.setPatronymic(request.getParameter(ParameterName.PATRONYMIC));
            client.setPassportId(request.getParameter(ParameterName.PASSPORT_ID));
            client.setHomePhone(request.getParameter(ParameterName.HOME_PHONE));
            client.setMobilePhone(request.getParameter(ParameterName.MOBILE_PHONE));
            client.setEmail(request.getParameter(ParameterName.EMAIL));

            String discount_card = request.getParameter(ParameterName.DISCOUNT_CARD_ID);

            if (discount_card != null && !discount_card.isEmpty()) {
                client.setDiscountCardId(Integer.parseInt(discount_card));
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

        return Optional.of(client);
    }

    public Optional<Order> buildOrder(HttpServletRequest request) {
        Order order = new Order();

        try {
            order.setClientId(Integer.parseInt(request.getParameter(ParameterName.CLIENT_ID)));
            order.setCarId(Integer.parseInt(request.getParameter(ParameterName.CAR_ID)));
            order.setStartDate(LocalDate.parse(request.getParameter(ParameterName.START_DATE)));
            order.setEndDate(LocalDate.parse(request.getParameter(ParameterName.END_DATE)));
        } catch (IllegalArgumentException | DateTimeParseException e) {
            return Optional.empty();
        }

        return Optional.of(order);
    }


}
