package util.Exception;

public class InvalidCreditTransactionException extends Exception {

    public InvalidCreditTransactionException() {
    }

    public InvalidCreditTransactionException(String msg) {
        super(msg);
    }
}
