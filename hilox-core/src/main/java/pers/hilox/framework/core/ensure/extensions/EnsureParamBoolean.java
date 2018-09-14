package pers.hilox.framework.core.ensure.extensions;

import pers.hilox.framework.core.ensure.EnsureParam;

/**
 * 布尔参数校验类
 * @auther hilox
 * @since 1.0
 */
public class EnsureParamBoolean extends EnsureParam<Boolean> {

    public EnsureParamBoolean(Boolean condition) {
        super(condition);
    }

    /**
     * false校验, 为true抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamBoolean isFalse(String errorCode) {
        if (hiloxObject) {
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
        if (!hiloxObject) {
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
        if (hiloxObject == null) {
            createHiloxException(errorCode);
        }
        return this;
    }
}
