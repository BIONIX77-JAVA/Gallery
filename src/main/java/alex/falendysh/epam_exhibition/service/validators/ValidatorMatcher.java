package alex.falendysh.epam_exhibition.service.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static alex.falendysh.epam_exhibition.util.Constants.ValidatorMatcherConstants.*;

public class ValidatorMatcher {

    private Map<String, String> results = new HashMap<>();
    private Map<String, String> invalidFields = new HashMap<>();
    private static Map<String, String> loginValidateFields = new HashMap<>();
    private static Map<String, String> passwordValidateFields = new HashMap<>();
    private Matcher matcher;

    public ValidatorMatcher() {
    }

    public Map<String, String> getInvalidFields() {
        return invalidFields;
    }

    public void initValidationFields() {
        loginValidateFields.put(USER_NAME_FIRST_CHARACTER_NOT_NUMBER, "login.first.is.number");
        loginValidateFields.put(USER_NAME_LENGTH_NOT_LESS_4_SYMBOLS, "login.less.4");
        loginValidateFields.put(USER_NAME_LENGTH_NOT_MORE_20_SYMBOLS, "login.more.then.20");
        loginValidateFields.put(USER_NAME_NOT_ALLOW_CHARACTERS, "login.illegal.characters");

        passwordValidateFields.put(USER_PASSWORD_NOT_ALLOW_CHARACTERS, "password.illegal.characters");
        passwordValidateFields.put(USER_PASSWORD_NOT_LESS_6_SYMBOLS, "password.less.6");
        passwordValidateFields.put(USER_PASSWORD_NUMBER_REQUIRED, "password.haven@t.got.number");
        passwordValidateFields.put(USER_PASSWORD_UPPERCASE_REQUIRED, "password.haven@t.got.uppercase");
        passwordValidateFields.put(USER_PASSWORD_LOWERCASE_REQUIRED, "password.haven@t.got.lowercase");
        passwordValidateFields.put(USER_PASSWORD_NOT_MORE_20_SYMBOLS, "password.more.then.20");
    }

    public boolean isValide() {
        for (Map.Entry<String, String> stringEntry : results.entrySet()) {
            if (stringEntry.getValue().equals("false")) {
                invalidFields.put(stringEntry.getKey().substring(0, stringEntry.getKey().indexOf(".")), stringEntry.getKey());
            }
        }

//* Returns true if this map contains no key-value mappings

        if (invalidFields.isEmpty()) return true;
        else return false;
    }


    public void checkTime(String time) {
        if (time == null || time.equals("")) {
            results.put("time.is.required.field", "false");
        } else {
            matcher = Pattern.compile(TIME24HOURS_PATTERN).matcher(time);
            if (!matcher.matches())
                results.put("time enter not correct", "false");
        }
    }

    public void checkUserPassword(String password) {
        if (password == null || password.equals("")) {
            results.put("password.is.required.field", "false");
        } else {
            for (String key : passwordValidateFields.keySet()) {
                matcher = Pattern.compile(key).matcher(password);
                if (!matcher.matches()) {
                    results.put(passwordValidateFields.get(key), "false");
                }
            }
        }
    }

    public void checkUserFirstName(String firstName) {
        if (firstName == null || firstName.equals("")) {
            results.put("firstName.is.required.field", "false");
        } else {
            matcher = Pattern.compile(USER_NAMES_NOT_ALLOW_CHARACTERS).matcher(firstName);
            if (!matcher.matches()) results.put("firstname.illegal.characters", "false");
            else {
                matcher = Pattern.compile(USER_NAMES_MORE_20).matcher(firstName);
                if (!matcher.matches()) results.put("firstname.more.then.20", "false");
            }
        }
    }

    public void checkUserLastName(String lastName) {
        if (lastName == null || lastName.equals("")) {
            results.put("lastName.is.required.field", "false");
        } else {
            matcher = Pattern.compile(USER_NAMES_NOT_ALLOW_CHARACTERS).matcher(lastName);
            if (!matcher.matches()) results.put("lastname.illegal.characters", "false");
            else {
                matcher = Pattern.compile(USER_NAMES_MORE_20).matcher(lastName);
                if (!matcher.matches()) results.put("lastname.more.then.20", "false");
            }
        }
    }


    public void checkUserPhone(String phone) {
        if (phone == null || phone.equals("")) {
            results.put("phone.is.required.field", "false");
        } else {
            matcher = Pattern.compile(TELEPHONE_NUMBER).matcher(phone);
            if (!matcher.matches()) results.put("telephone.is.incorrect", "false");
        }
    }

    public void checkEmail(String email) {
        if (email == null || email.equals("")) {
            results.put("email.is.required.field", "false");

        } else {
            matcher = Pattern.compile(EMAIL).matcher(email);
            if (!matcher.matches()) results.put("email.is.incorrect", "false");
        }

    }
}