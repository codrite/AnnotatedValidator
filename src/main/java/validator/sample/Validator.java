package validator.sample;


import validator.definition.ClassValidator;
import validator.definition.FieldValidator;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public abstract class Validator<T> {

    protected T element;

    public void validate(T element) {
        this.element = element;
        Class<?> thisClass = element.getClass();

        if (thisClass.isAnnotationPresent(ClassValidator.class)) {
            validateField();
        }

    }

    protected void validateField() {
        for (Field field : element.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(FieldValidator.class)) {
                FieldValidator annotation = field.getAnnotation(FieldValidator.class);
                if(annotation.required()) {
                    field.setAccessible(true);
                    validateFieldAttributes(field, annotation.min(), annotation.max(), annotation.regex());
                }
            }
        }
    }

    protected boolean verifyRegEx(final String regex, final String value) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(value).matches();
    }

    public abstract void validateFieldAttributes(Field field, String minLength, String maxLength, String regex);

}
