package pers.hilox.framework.core.exceptions;

/**
 * 空请求体异常
 * @auther hilox
 * @since 1.0
 */
public class HiloxEmptyRequestBodyException extends HiloxBusinessException {

    public HiloxEmptyRequestBodyException(String message) {
        super(message);
    }

    public HiloxEmptyRequestBodyException(String code, String message) {
        super(code, message);
    }

    public HiloxEmptyRequestBodyException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    public HiloxEmptyRequestBodyException(String code, String message, String internalMessage) {
        super(code, message, internalMessage);
    }

    public HiloxEmptyRequestBodyException(String code, String message, String internalMessage, Throwable throwable) {
        super(code, message, internalMessage, throwable);
    }
}
