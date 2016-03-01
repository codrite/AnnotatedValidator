package validator.sample;

import java.lang.reflect.Field;

public class StringValidatorImpl<T> extends Validator<T> {

    @Override
    public void validateFieldAttributes(Field field, String minLength, String maxLength, String regex) {
        String elementValue = "";
        try {
            elementValue = (String) field.get(element);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if(elementValue.length() < Integer.parseInt(minLength) || elementValue.length() > Integer.parseInt(maxLength) ) {
            throw new RuntimeException("Validation check failed");
        }

        verifyRegEx(elementValue, regex);
    }

}
