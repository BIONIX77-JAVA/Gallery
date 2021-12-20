package alex.falendysh.epam_exhibition.service.validators;

public enum NameValidator {

    NAME_VALIDATOR;

    public boolean isValid(String name) {
        if (name == null){
            return false;
        }
        return !name.isEmpty();
    }
}