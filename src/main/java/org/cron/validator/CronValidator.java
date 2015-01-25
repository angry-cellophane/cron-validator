package org.cron.validator;

import org.cron.validator.result.ValidationResult;

public interface CronValidator {
    ValidationResult validate(String cronExpression);
}
