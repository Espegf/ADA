package egf.myshop.persistence.exception;

/**
 * @author espeg
 */
public class ShopException extends Exception {
    private String message;
    private int code;

    public ShopException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
