package pers.hilox.framework.core.ensure.extensions;

/**
 * @author hilox
 * @since 1.0
 */
public class EnsureParamString extends EnsureParamObject {

    private String string;

    public EnsureParamString(String string) {
        super(string);
        this.string = string;
    }

    /**
     * 空校验, 为null抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamObject isNotNull(String errorCode) {
        return super.isNotNull(errorCode);
    }

    public <HiloxObject> EnsureParamObject isEqualTo(HiloxObject obj, String errorCode) {
        return super.isEqualTo(obj, errorCode);
    }

    public <HiloxObject> EnsureParamObject isNotEqualTo(HiloxObject obj, String errorCode) {
        return super.isNotEqualTo(obj, errorCode);
    }
}
