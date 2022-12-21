package egf.myshop.persistence.exception;

/**
 * @author espeg
 */
public class ShopException extends Exception {
    private String menssage;
    private int code;

    public ShopException(String menssage, int code) {
        this.menssage = menssage;
        this.code = code;
    }

    public String getMenssage() {
        return menssage;
    }

    public int getCode() {
        return code;
    }
}
