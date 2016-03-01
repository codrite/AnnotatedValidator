package annotatedvalidator.sample;

import annotatedvalidator.definition.ClassValidator;
import annotatedvalidator.definition.FieldValidator;
import annotatedvalidator.definition.StringValidatorImpl;

@ClassValidator(validatorClass = StringValidatorImpl.class)
public class Element {

    @FieldValidator(max = "10", min = "5", regex = "[A-Za-z]*", required = true)
    private String name;

    @FieldValidator(max = "10", min = "2", regex = "[A-Za-z]*", required = true)
    private String value;

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

}