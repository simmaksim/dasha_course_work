package validation;

public class RegExProvider {
    public final static String LETTERS = "[а-яА-Яa-zA-Z]+";
    public final static String LATIN_LETTERS_WITH_NUMBERS_AND_SYMBOLS = "[a-zA-Z0-9/*-+_@.]+";
    public final static String PASSPORT_ID = "[A-Z]{2}[0-9]{7}";
    public final static String NUMBERS_AND_SYMBOLS = "[0-9+-*/#()]";
}
