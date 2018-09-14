package pers.hilox.framework.core.ensure;

import pers.hilox.framework.core.exceptions.HiloxExceptionFactory;

/**
 * 参数校验基类
 * @author hilox
 * @since 1.0
 */
public class EnsureParam<HiloxObject> {

    protected HiloxObject hiloxObject;

    public EnsureParam(HiloxObject hiloxObject) {
        this.hiloxObject = hiloxObject;
    }

    /**
     * 异常创建
     * @param errorCode 错误代码
     */
    protected void createHiloxException(String errorCode) {
        throw HiloxExceptionFactory.create(errorCode);
    }
}
