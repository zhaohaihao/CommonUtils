package pers.hilox.framework.core.ensure.extensions;

import pers.hilox.framework.core.ensure.EnsureParam;

/**
 * 布尔参数校验类
 * @auther hilox
 * @since 1.0
 */
public class EnsureParamBoolean extends EnsureParam<Boolean> {

    private Boolean condition;

    public EnsureParamBoolean(Boolean condition) {
        super(condition);
        this.condition = condition;
    }

    /**
     * false校验, 为true抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamBoolean isFalse(String errorCode) {
        if (condition) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * true校验, 为false抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamBoolean isTrue(String errorCode) {
        if (!condition) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 空校验, 为null抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamBoolean isNotNull(String errorCode) {
        if (condition == null) {
            createHiloxException(errorCode);
        }
        return this;
    }
}
