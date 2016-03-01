package annotatedvalidator;


import annotatedvalidator.definition.ClassValidator;
import annotatedvalidator.definition.Validator;
import annotatedvalidator.sample.Element;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Element element = new Element();
        element.setName("Arnabh");
        element.setValue("Sau");
        validate(element);
    }

    @SuppressWarnings("unchecked")
    public static <T> void validate(T target) throws IllegalAccessException, InstantiationException {
        ClassValidator classValidator = target.getClass().getAnnotation(ClassValidator.class);
        Validator<T> validator = (Validator<T>) classValidator.validatorClass().newInstance();

        validator.validate(target);

        if(validator.getMessages() > 0) {
            for (String errorMessage : validator.getErrorMessage()) {
                System.out.println(errorMessage);
            }
        }
    }


}
