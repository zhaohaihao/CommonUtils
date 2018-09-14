package pers.hilox.framework.core.ensure.extensions;

import pers.hilox.framework.core.ensure.EnsureParam;

/**
 * 枚举参数校验类
 * @author hilox
 * @since 1.0
 */
public class EnsureParamEnum extends EnsureParam<Enum> {

    public EnsureParamEnum(Enum anEnum) {
        super(anEnum);
    }

    /**
     * 空校验, 为null抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamEnum isNotNull(String errorCode) {
        if (hiloxObject == null) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 相等校验, 不相等抛异常
     * @param comparedEnum 比较枚举
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamEnum isEqual(Enum comparedEnum, String errorCode) {
        if (hiloxObject != comparedEnum) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 不相等校验, 相等抛异常
     * @param comparedEnum 比较枚举
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamEnum isNotEqual(Enum comparedEnum, String errorCode){
        if(hiloxObject == comparedEnum){
            createHiloxException(errorCode);
        }
        return this;
    }
}
