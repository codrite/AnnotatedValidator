package annotatedvalidator.definition;

import junit.framework.Assert;
import org.junit.Test;

public class ValidatorTest {

    @Test
    public void testVerifyRegEx() {
        StringValidatorImpl stringValidator = new StringValidatorImpl();
        Assert.assertTrue(stringValidator.verifyRegEx("[a-z]*", "arnabh"));
    }

}