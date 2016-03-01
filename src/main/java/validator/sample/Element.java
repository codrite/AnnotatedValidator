package validator.sample;

import validator.definition.ClassValidator;
import validator.definition.FieldValidator;

@ClassValidator(validatorClass = Validator.class)
public class Element {

    @FieldValidator(max = "10", min = "5", regex = "", required = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
