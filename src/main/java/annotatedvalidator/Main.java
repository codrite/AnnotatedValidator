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
    public static void validate(Element element) throws IllegalAccessException, InstantiationException {
        ClassValidator classValidator = element.getClass().getAnnotation(ClassValidator.class);
        Validator<Element> validator = (Validator<Element>) classValidator.validatorClass().newInstance();
        validator.validate(element);
        for(String errorMessage : validator.getErrorMessage()) {
            System.out.println(errorMessage);
        }
    }


}
