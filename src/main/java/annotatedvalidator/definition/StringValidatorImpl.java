package annotatedvalidator.definition;

import annotatedvalidator.sample.Element;

import java.lang.reflect.Field;

public class StringValidatorImpl extends Validator<Element> {

    public StringValidatorImpl() {
        super(Element.class);
    }

    @Override
    public void validateFieldAttributes(Field field, String min, String max, String regex) {
        String elementValue = getValue(field);
        StringBuilder cause = new StringBuilder("Field : " + field.getName() + "\tvalue : " + elementValue + " \t[ ");
        int initialLength = cause.length();

        if(elementValue.length() < Integer.parseInt(min) || elementValue.length() > Integer.parseInt(max)) {
            cause.append(" length validation, ");
        }

        if( !verifyRegEx(regex, elementValue) ) {
            cause.append(" regular expression ");
        }

        cause.append(" ] ");

        if(cause.length() > initialLength) {
            this.errorMessage[this.messages++] = cause.toString();
        }
    }

}
