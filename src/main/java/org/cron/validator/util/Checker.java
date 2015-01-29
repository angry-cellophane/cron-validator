package org.cron.validator.util;


import org.cron.validator.Errors;
import org.cron.validator.result.ValidationResult;

import static org.cron.validator.Errors.ALL_OR_NUMBER;
import static org.cron.validator.Errors.NON_ZERO;
import static org.cron.validator.Errors.NUMBER_0_5;
import static org.cron.validator.Errors.NUMBER_0_9;
import static org.cron.validator.result.Results.Error;
import static org.cron.validator.result.Results.Success;

public class Checker {

    private static final char ALL = '*';
    private static final char ZERO = '0';
    private static final char NINE = '9';
    private static final char FIVE = '5';

    public static boolean from0to9(char c){
        return c >= ZERO && c <= NINE;
    }

    public static boolean from0to5(char c){
        return c >= ZERO && c <= FIVE;
    }

    public static ValidationResult checkNumbers(String cron, int i) {
        char d1 = cron.charAt(i);
        if (!from0to5(d1)) return Error(NUMBER_0_5, cron, i);
        char d2 = cron.charAt(i + 1);
        if (!from0to9(d2)) return Error(NUMBER_0_9, cron, i + 1);

        return Success();
    }

    public static ValidationResult checkNonZeroNumbers(String cron, int i) {
        char d1 = cron.charAt(i);
        if (!from0to5(d1)) return Error(NUMBER_0_5, cron, i);
        char d2 = cron.charAt(i + 1);
        if (!from0to9(d2)) return Error(NUMBER_0_9, cron, i + 1);

        if (d1 == '0' && d2 == '0') return Error(NON_ZERO, cron, i);

        return Success();
    }

    public static ValidationResult checkNumber(String cron, int i) {
        if (from0to9(cron.charAt(i))) return Success();

        return Error(NUMBER_0_9, cron, i + 1);
    }

    public static ValidationResult checkAllOrNumber(String cron, int i) {
        char c = cron.charAt(i);
        if (c == ALL || from0to9(c)) return Success();

        return Error(ALL_OR_NUMBER, cron, i + 1);
    }

    private Checker(){}
}
