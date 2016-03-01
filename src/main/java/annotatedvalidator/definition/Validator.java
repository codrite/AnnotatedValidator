package annotatedvalidator.definition;


import java.lang.reflect.Field;
import java.util.regex.Pattern;

public abstract class Validator<T> {

    protected int messages;
    protected String[] errorMessage;
    protected T element;

    public Validator(Class clazz) {
        this.errorMessage = new String[clazz.getDeclaredFields().length];
    }

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
        boolean matches = pattern.matcher(value).matches();
        return matches;
    }

    @SuppressWarnings("unchecked")
    protected <S> S getValue(Field field) {
        S elementValue;
        try {
            elementValue = (S) field.get(element);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return elementValue;
    }

    public int getMessages() {
        return messages;
    }

    public String[] getErrorMessage() {
        return errorMessage;
    }

    public abstract void validateFieldAttributes(Field field, String min, String max, String regex);

}
