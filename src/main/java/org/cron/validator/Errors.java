package org.cron.validator;

/**
 * Created by alexander on 28.01.15.
 */
public class Errors {

    public static final String EMPTY_VALUE = "Second value cannot be empty";
    public static final String UNEXPECTED_SYMBOL = "Unexpected symbol";
    public static final String ALL_OR_NUMBER = "Unexpected symbol. Expected * or a number from 0 to 9";
    public static final String NUMBER = "Unexpected symbol. Expected a number from 0 to 9";
    public static final String NUMBER_0_9 = "Expected a number from 0 to 9";
    public static final String NUMBER_0_5 = "Expected a number from 0 to 5";
    public static final String SLASH = "Expected '/'";
    public static final String RANGE = "Should be bigger than the left range number";
    public static final String NON_ZERO = "Should be > 0";

    private Errors(){}
}
