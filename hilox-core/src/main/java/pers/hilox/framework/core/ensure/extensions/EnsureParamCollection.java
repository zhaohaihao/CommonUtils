package pers.hilox.framework.core.ensure.extensions;

import pers.hilox.framework.core.ensure.EnsureParam;

import java.util.Collection;

/**
 * 集合参数校验类
 * @author hilox
 * @since 1.0
 */
public class EnsureParamCollection extends EnsureParam<Collection> {

    public EnsureParamCollection(Collection collection) {
        super(collection);
    }

    /**
     * 空校验, 为null抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamCollection isNotNull(String errorCode) {
        if (hiloxObject == null) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 空校验, 为null或者大小为0抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamCollection isNotEmpty(String errorCode) {
        if (hiloxObject == null || hiloxObject.size() == 0) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 相等校验, 不相等抛异常
     * @param comparedCollection 比较集合
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamCollection isEqualTo(Collection comparedCollection, String errorCode) {
        if (!(hiloxObject == null ? comparedCollection == null : hiloxObject.equals(comparedCollection))) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 不相等校验, 相等抛异常
     * @param comparedCollection 比较集合
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamCollection isNotEqualTo(Collection comparedCollection, String errorCode) {
        if (hiloxObject == null ? comparedCollection == null : hiloxObject.equals(comparedCollection)) {
            createHiloxException(errorCode);
        }
        return this;
    }

}
