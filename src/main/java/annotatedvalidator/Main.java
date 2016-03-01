package annotatedvalidator;


import annotatedvalidator.definition.ClassValidator;
import annotatedvalidator.definition.Validator;
import annotatedvalidator.sample.Element;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Element element = new Element();
        element.setName("Arnabh");
        element.setValue("Sau");
        String[] errorMessages = validate(element);

        for(String eMessage : errorMessages) {
            System.out.println(eMessage);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> String[] validate(T target) throws IllegalAccessException, InstantiationException {
        ClassValidator classValidator = target.getClass().getAnnotation(ClassValidator.class);
        Validator<T> validator = (Validator<T>) classValidator.validatorClass().newInstance();

        validator.validate(target);

        String[] errorMessages = new String[validator.getMessages()];
        System.arraycopy(validator.getErrorMessage(), 0, errorMessages, 0, validator.getMessages());

        return errorMessages;
    }


}
