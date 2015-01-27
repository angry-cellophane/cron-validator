package org.cron.validator.seconds;

import junit.framework.Assert;
import org.cron.validator.CronValidator;
import org.cron.validator.result.ValidationResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.cron.validator.result.Results.Success;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SecondsSuccessValueValidatorTest {

    @Parameters
    public static Collection<Object[]> dataset() {
        Collection<Object[]> data = new ArrayList<>();

//        check 0, 1, 2, .., 9
        for (int d1 = 0; d1 < 9; d1++) {
            data.add(new Object[] {Integer.toString(d1), Success()});
        }

//        check 00, 01, 02, .., 58, 59
        for (int d1 = 0; d1 < 6; d1++) {
            for (int d2 = 0; d2 < 9; d2++) {
                data.add(new Object[] {d1 + ""+d2, Success()});
            }
        }

//        check 0-1, 0-2, .., 5-6,5-7, .., 8-9
        for (int d1 = 0; d1 < 9; d1++) {
            for (int d2 = d1+1; d2 < 10; d2++) {
                data.add(new Object[] { d1+"-"+d2, Success()});
            }
        }

//        check 0-01, 0-02, .., 5-10, 5-11, .., 5-44, 5-45, .., 9-58, 9-59
        for (int d1 = 0; d1 < 10; d1++) {
            for (int d2 = d1 == 0 ? 0 : 1; d2 < 6; d2++) {
                for (int d3 = d2 == 0 ? 1 : 0; d3 < 10; d3++) {
                    data.add(new Object[] { d1+"-"+d2+""+d3, Success()});
                }
            }
        }

//        check 0/1, 0/2, .., 9/9
        for (int d1 = 0; d1 < 10; d1++) {
            for (int d2 = 1; d2 < 10; d2++) {
                data.add(new Object[] { d1+"/"+d2, Success()});
            }
        }

//        check 0/01, 0/02, .. 9/59
        for (int d1 = 0; d1 < 10; d1++) {
            for (int d2 = 0; d2 < 6; d2++) {
                for (int d3 = d2 == 0 ? 1 : 0; d3 < 10; d3++) {
                    data.add(new Object[] { d1+"/"+d2+""+d3, Success()});
                }
            }
        }

//        check 00/1, 00/2, .., 59/9
        for (int d1 = 0; d1 < 6; d1++) {
            for (int d2 = d1 == 0 ? 1 : 0; d2 < 10; d2++) {
                for (int d3 = 1; d3 < 10; d3++) {
                    data.add(new Object[] { d1+""+d2+"/"+d3, Success()});
                }
            }
        }

//        check 00/01, 00/02, .., 59/59
        for (int d1 = 0; d1 < 6; d1++) {
            for (int d2 = d1 == 0 ? 1 : 0; d2 < 10; d2++) {
                for (int d3 = 0; d3 < 6; d3++) {
                    for (int d4 = d3 == 0 ? 1 : 0; d4 < 10; d4++) {
                        data.add(new Object[] { d1+""+d2+"/"+d3+""+d4, Success()});
                    }
                }
            }
        }

//        check */1, */2, .., */9
        for (int d1 = 1; d1 < 10; d1++) {
            data.add(new Object[] { "*/"+d1, Success()});
        }

//        check */01, */02, .., */59
        for (int d1 = 0; d1 < 6; d1++) {
            for (int d2 = d1 == 0? 1 : 0; d2 < 10; d2++) {
                data.add(new Object[] { "*/"+d1+""+d2, Success()});
            }
        }

        data.add(new Object[] { "*", Success()});

        return data;
    }

    private final String expression;
    private final ValidationResult expectedResult;

    public SecondsSuccessValueValidatorTest(String expression, ValidationResult expectedResult) {
        this.expression = expression;
        this.expectedResult = expectedResult;
    }

    private final CronValidator validator = new SecondsValueValidator();

    @Test
    public void testValidation() {
        ValidationResult actual = validator.validate(expression);
        Assert.assertEquals(expression +"| expected: "+expectedResult+", but get "+actual, expectedResult, actual);
    }
}