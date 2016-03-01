package validator;


import validator.sample.Element;
import validator.sample.StringValidatorImpl;

public class AnnotatedValidator {

    public static void main(String[] args) throws IllegalAccessException {
        Element element = new Element();
        element.setName("Arn");
        new StringValidatorImpl<Element>().validate(element);
    }

}
