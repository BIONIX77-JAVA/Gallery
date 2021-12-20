package alex.falendysh.epam_exhibition.service.validators;

public enum EmailValidator {

    EMAIL_VALIDATOR;

    public boolean isValid(String email) {
        if (email == null) {
            return false;
        }
        String regex = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        return email.matches(regex);
    }
}