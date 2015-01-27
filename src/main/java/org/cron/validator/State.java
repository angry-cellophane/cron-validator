package org.cron.validator;

import org.cron.validator.result.ResultType;
import org.cron.validator.result.ValidationResult;

public interface State {
    ValidationResult validate();
}
