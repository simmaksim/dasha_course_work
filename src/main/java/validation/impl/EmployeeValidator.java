package validation.impl;

import entity.Employee;
import validation.RegExProvider;
import validation.Validator;

public class EmployeeValidator extends Validator<Employee> {
    private final static int MIN_PASSWORD_LENGTH = 4;
    private final static int MAX_PASSWORD_LENGTH = 50;
    private final static int MIN_LOGIN_LENGTH = 5;
    private final static int MAX_LOGIN_LENGTH = 50;

    @Override
    public boolean isValid(Employee entity) {
        String login = entity.getLogin();
        String password = entity.getPassword();

        if (!validateString(login, MIN_LOGIN_LENGTH, MAX_LOGIN_LENGTH, RegExProvider.LATIN_LETTERS_WITH_NUMBERS_AND_SYMBOLS)) {
            setValidationFeedback("Неверный формат логина");
            return false;
        }

        if (!validateString(password, MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH, RegExProvider.LATIN_LETTERS_WITH_NUMBERS_AND_SYMBOLS)) {
            setValidationFeedback("Неверный формат пароля");
            return false;
        }

        return true;
    }
}
