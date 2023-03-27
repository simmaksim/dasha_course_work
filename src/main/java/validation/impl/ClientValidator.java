package validation.impl;

import entity.Client;
import validation.RegExProvider;
import validation.Validator;

public class ClientValidator extends Validator<Client> {
    private final static int MIN_SHORT_TEXT_LENGTH = 3;
    private final static int MAX_SHORT_TEXT_LENGTH = 50;
    private final static int MIN_PASSPORT_ID_LENGTH = 9;
    private final static int MAX_PASSPORT_ID_LENGTH = 9;
    private final static int MIN_PHONE_LENGTH = 6;
    private final static int MAX_PHONE_LENGTH = 20;

    @Override
    public boolean isValid(Client entity) {
        String name = entity.getName();
        String surname = entity.getSurname();
        String patronymic = entity.getPatronymic();
        String passportId = entity.getPassportId();
        String homePhone = entity.getHomePhone();
        String mobilePhone = entity.getMobilePhone();
        String email = entity.getEmail();

        if (!validateString(name, MIN_SHORT_TEXT_LENGTH, MAX_SHORT_TEXT_LENGTH, RegExProvider.LETTERS)) {
            setValidationFeedback("Неверный формат имени");
            return false;
        }

        if (!validateString(surname, MIN_SHORT_TEXT_LENGTH, MAX_SHORT_TEXT_LENGTH, RegExProvider.LETTERS)) {
            setValidationFeedback("Неверный формат фамилии");
            return false;
        }

        if (!validateString(patronymic, MIN_SHORT_TEXT_LENGTH, MAX_SHORT_TEXT_LENGTH, RegExProvider.LETTERS)) {
            setValidationFeedback("Неверный формат отчества");
            return false;
        }

        if (!validateString(passportId, MIN_PASSPORT_ID_LENGTH, MAX_PASSPORT_ID_LENGTH, RegExProvider.PASSPORT_ID)) {
            setValidationFeedback("Неверный формат номера паспорта");
            return false;
        }

        if (homePhone != null && !homePhone.isEmpty() &&
                !validateString(homePhone, MIN_PHONE_LENGTH, MAX_PHONE_LENGTH, RegExProvider.NUMBERS_AND_SYMBOLS)) {
            setValidationFeedback("Неверный формат домашнего телефона");
            return false;
        }

        if (mobilePhone != null && !mobilePhone.isEmpty() &&
                !validateString(mobilePhone, MIN_PHONE_LENGTH, MAX_PHONE_LENGTH, RegExProvider.NUMBERS_AND_SYMBOLS)) {
            setValidationFeedback("Неверный формат мобильного телефона");
            return false;
        }

        if (email != null && !email.isEmpty() &&
                !validateString(email, MIN_SHORT_TEXT_LENGTH, MAX_SHORT_TEXT_LENGTH, RegExProvider.LATIN_LETTERS_WITH_NUMBERS_AND_SYMBOLS)) {
            setValidationFeedback("Неверный формат email");
            return false;
        }

        return true;
    }
}
