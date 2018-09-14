package pers.hilox.framework.core.ensure.extensions;

import pers.hilox.framework.core.ensure.EnsureParam;

/**
 * 数字参数校验类
 * @author hilox
 * @since 1.0
 */
public class EnsureParamNumber extends EnsureParam<Number> {

    private Double number;

    public EnsureParamNumber(Number number) {
        super(number);
        if (number != null) {
            this.number = number.doubleValue();
        }
    }

    /**
     * 空校验, 为null抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamNumber isNotNull(String errorCode) {
        if (number == null) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 相等校验, 不相等抛异常
     * @param comparedNumber 比较数字
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamNumber isEqualTo(Number comparedNumber, String errorCode) {
        if (number != comparedNumber.doubleValue()) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 不相等校验, 相等抛异常
     * @param comparedNumber 比较数字
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamNumber isNotEqualTo(Number comparedNumber, String errorCode) {
        if (number == comparedNumber.doubleValue()) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 大小校验, 小于等于抛异常
     * @param comparedNumber
     * @param errorCode
     * @return
     */
    public EnsureParamNumber isGt(Number comparedNumber, String errorCode) {
        if (number <= comparedNumber.doubleValue()) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 大小校验, 大于抛异常
     *
     * @param comparedNumber
     * @param errorCode
     * @return
     */
    public EnsureParamNumber isNotGt(Number comparedNumber, String errorCode) {
        if (number > comparedNumber.doubleValue()) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 大小校验, 大于等于抛异常
     * @param comparedNumber
     * @param errorCode
     * @return
     */
    public EnsureParamNumber isLt(Number comparedNumber, String errorCode) {
        if (number >= comparedNumber.doubleValue()) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 大小校验, 小于抛异常
     * @param comparedNumber
     * @param errorCode
     * @return
     */
    public EnsureParamNumber isNotLt(Number comparedNumber, String errorCode) {
        if (number < comparedNumber.doubleValue()) {
            createHiloxException(errorCode);
        }
        return this;
    }
}
