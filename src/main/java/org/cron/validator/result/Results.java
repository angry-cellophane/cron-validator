package org.cron.validator.result;

import static org.cron.validator.result.ResultType.ERROR;
import static org.cron.validator.result.ResultType.VALID;
import static org.cron.validator.result.ResultType.WARNING;

public class Results {

    private static class Message implements MessageValidationResult {

        private final ResultType type;
        private final String message;

        private Message(ResultType type, String message) {
            this.type = type;
            this.message = message;
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
            return type+" : " + message;
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

    public static MessageValidationResult Error(String message) {
        return new Message(ERROR, message);
    }

    public static MessageValidationResult Warning(String message) {
        return new Message(WARNING, message);
    }

    private Results(){}
}
