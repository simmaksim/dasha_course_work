package validation;

public abstract class Validator<T> {
    private String validationFeedback;

    public abstract boolean isValid(T entity);

    protected void setValidationFeedback(String feedback)
    {
        validationFeedback = feedback;
    }

    protected boolean validateString(String str, int minLength, int maxLength, String regex) {
        return str != null && str.length() <= maxLength && str.length() >= minLength &&
                str.matches(regex);
    }

    public String getValidationFeedback()
    {
        return validationFeedback;
    }
}
