package kudangkoding.gamifikasi.exceptions;

public class InvalidParameterValueException  extends BusinessException{
    public InvalidParameterValueException(String message) {
        super(message);
    }

    public InvalidParameterValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
