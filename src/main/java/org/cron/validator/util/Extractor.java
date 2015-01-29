package org.cron.validator.util;

/**
 * Created by alexander on 30.01.15.
 */
public class Extractor {

    public static int numberFrom(String cron, int index) {
        return cron.charAt(index) - '0';
    }

    public static int numbersFrom(String cron, int index) {
        int d1 = numberFrom(cron, index);
        int d2 = numberFrom(cron, index + 1);

        if (d1 == 0) return d2;

        return d1 * 10 + d2;
    }

    private Extractor() {}
}
