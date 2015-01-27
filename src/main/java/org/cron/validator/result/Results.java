package org.cron.validator.result;

import java.util.Arrays;

import static org.cron.validator.result.ResultType.ERROR;
import static org.cron.validator.result.ResultType.VALID;
import static org.cron.validator.result.ResultType.WARNING;

public class Results {

    private static class Message implements MessageValidationResult {

        private final ResultType type;
        private final String message;

        private Message(ResultType type, String message, String cron, int index) {
            this.type = type;
            this.message = createMessage(message, cron, index);
        }

        private String createMessage(String message, String cron, int index) {
            return new StringBuilder(cron.length() << 2)
                     .append(cron)
                     .append('\n')
                     .append(tabs(index))
                     .append("^- ")
                     .append(message)
                     .toString();
        }

        private char[] tabs(int index) {
            if (index == 0) return new char[0];

            char[] c = new char[index - 1];
            Arrays.fill(c, ' ');
            return c;
        }

        @Override
        public String message() {
            return message;
        }

        @Override
        public ResultType resultType() {
            return type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Message message1 = (Message) o;

            if (!message.equals(message1.message)) return false;
            if (type != message1.type) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = type.hashCode();
            result = 31 * result + message.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return type+":\n" + message;
        }
    }

    private static final ValidationResult SUCCESS = new ValidationResult() {
        @Override public ResultType resultType() {
            return VALID;
        }

        @Override public String toString() {
            return "Success";
        }
    };

    public static ValidationResult Success() {
        return SUCCESS;
    }

    public static MessageValidationResult Error(String message, String cronExpression, int index) {
        return new Message(ERROR, message, cronExpression, index);
    }

    public static MessageValidationResult Warning(String message, String cronExpression, int index) {
        return new Message(WARNING, message, cronExpression, index);
    }

    private Results(){}
}
