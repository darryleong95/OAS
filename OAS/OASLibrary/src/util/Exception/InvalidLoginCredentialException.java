package util.Exception;

public class InvalidLoginCredentialException extends Exception {

    public InvalidLoginCredentialException() {
    }

    public InvalidLoginCredentialException(String msg) {
        super(msg);
    }
}
