package pers.hilox.framework.core.exceptions;

/**
 * 业务异常
 * @author hilox
 * @since 1.0
 */
public class HiloxBusinessException extends RuntimeException {

    private static final String DEFAULT_FAULT_CODE = "HH0001";

    /**
     * 错误代码
     */
    private String code;

    /**
     * 异常描述信息
     */
    private String message;

    public HiloxBusinessException(String message) {
        this(DEFAULT_FAULT_CODE, message, new Throwable());
    }

    public HiloxBusinessException(String code, String message) {
        this(code, message, new Throwable());
    }

    public HiloxBusinessException(String code, String message, Throwable throwable) {
        this(code, message, throwable.getMessage(), throwable);
    }

    public HiloxBusinessException(String code, String message, String internalMessage) {
        this(code, message, internalMessage, null);
    }

    public HiloxBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public HiloxBusinessException(String code, String message, String internalMessage, Throwable throwable) {
        super("[" + code + "] - " + message + internalMessage, throwable);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return "[" + code + "]" + "-" + message;
    }

    public String getMessageWithoutCode() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
