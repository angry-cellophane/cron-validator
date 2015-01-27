package org.cron.validator.seconds;

import org.cron.validator.CronValidator;
import org.cron.validator.result.ValidationResult;

import static org.cron.validator.Errors.EMPTY_VALUE;
import static org.cron.validator.Errors.RANGE;
import static org.cron.validator.Errors.SLASH;
import static org.cron.validator.Errors.SLASH_0;
import static org.cron.validator.Errors.UNEXPECTED_SYMBOL;
import static org.cron.validator.result.ResultType.VALID;
import static org.cron.validator.result.Results.Error;
import static org.cron.validator.result.Results.Success;
import static org.cron.validator.util.Checker.checkAllOrNumber;
import static org.cron.validator.util.Checker.checkNumber;
import static org.cron.validator.util.Checker.checkNumbers;

public class SecondsValueValidator implements CronValidator {

    @Override
    public ValidationResult validate(String cronExpression) {
        if (cronExpression.contains(",")) throw new IllegalArgumentException("Validator doesn't support lists yet");
        if (cronExpression == null || cronExpression.length() == 0) return Error(EMPTY_VALUE, cronExpression, 0);

        switch (cronExpression.length()) {
            case 1 :
                return one(cronExpression);
            case 2:
                return two(cronExpression);
            case 3:
                return three(cronExpression);
            case 4:
                return four(cronExpression);
            case 5:
                return five(cronExpression);
            default:
                return Error(UNEXPECTED_SYMBOL, cronExpression, 6);
        }
    }

    private ValidationResult one(String cron) {
        return checkNumber(cron, 0);
    }

    private ValidationResult two(String cron) {
        return checkNumbers(cron, 0, 1);
    }

    private ValidationResult three(String cron) {
        ValidationResult result = checkAllOrNumber(cron, 0);
        if (result.resultType() != VALID) return result;

        result = checkNumber(cron, 2);
        if (result.resultType() != VALID) return result;

        switch (cron.charAt(1)) {
            case '-':
                return (cron.charAt(0) != '*' && (cron.charAt(0) < cron.charAt(2)) )
                        ? Success()
                        : Error(RANGE, cron, 2);
            case '/':
                return cron.charAt(2) != '0'
                        ? Success()
                        : Error(SLASH_0, cron, 2);
            default:
                return Error(UNEXPECTED_SYMBOL, cron, 1);
        }
    }

    private ValidationResult four(String cron) {
        return null;
    }

    private ValidationResult five(String cron) {
        ValidationResult result = checkNumbers(cron, 0, 1);
        if (result.resultType() != VALID) return result;

        result = checkNumbers(cron, 3, 4);
        if (result.resultType() != VALID) return result;

        if (cron.charAt(2) != '/') return Error(SLASH, cron, 2);

        return Success();
    }
}
