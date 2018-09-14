package pers.hilox.framework.core.exceptions;

/**
 * 系统异常
 * @author hilox
 * @since 1.0
 */
public class HiloxSystemException extends RuntimeException {

    public HiloxSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
