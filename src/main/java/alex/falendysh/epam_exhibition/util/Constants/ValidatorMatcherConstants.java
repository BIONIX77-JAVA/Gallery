package alex.falendysh.epam_exhibition.util.Constants;

public class ValidatorMatcherConstants {
    public static final String USER_NAME_FIRST_CHARACTER_NOT_NUMBER = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,}$";
    public static final String USER_NAME_LENGTH_NOT_MORE_20_SYMBOLS = "(.){0,20}$";
    public static final String USER_NAME_LENGTH_NOT_LESS_4_SYMBOLS = "^(.){4,}";
    public static final String USER_NAME_NOT_ALLOW_CHARACTERS = "^[a-zA-Z0-9-_\\.]{0,}$";

    public static final String USER_PASSWORD_NUMBER_REQUIRED = "^(?=.*\\d).*$";
    public static final String USER_PASSWORD_UPPERCASE_REQUIRED = "^(?=.*[A-Z]).*$";
    public static final String USER_PASSWORD_LOWERCASE_REQUIRED = "^(?=.*[a-z]).*$";
    public static final String USER_PASSWORD_NOT_ALLOW_CHARACTERS = "^[a-zA-Z0-9]{0,}$";
    public static final String USER_PASSWORD_NOT_LESS_6_SYMBOLS = "^(.){6,}$";
    public static final String USER_PASSWORD_NOT_MORE_20_SYMBOLS = "^(.){0,20}$";

    public static final String USER_NAMES_NOT_ALLOW_CHARACTERS = "^[a-zA-Zа-яА-я]{1,}$";
    public static final String USER_NAMES_MORE_20 = "^(.){0,20}$";

    public static final String TELEPHONE_NUMBER = "^(8|\\+7)\\d{10}$";
    public static final String EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    public static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
}