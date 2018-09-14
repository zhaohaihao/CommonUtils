package pers.hilox.framework.core.ensure.extensions;

import pers.hilox.framework.core.ensure.EnsureParam;

/**
 * Object参数校验类
 * @auther hilox
 * @since 1.0
 */
public class EnsureParamObject extends EnsureParam<Object> {

    private boolean isSatisfied;

    public EnsureParamObject(Object obj) {
        super(obj);
    }

    /**
     * 空校验, 为null抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamObject isNotNull(String errorCode) {
        if (hiloxObject == null) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 相等校验, 不相等抛异常
     * @param obj 比较对象
     * @param errorCode 错误代码
     * @param <HiloxObject> 参数类型
     * @return
     */
    public <HiloxObject extends Object> EnsureParamObject isEqualTo(HiloxObject obj, String errorCode) {
        isSatisfied = (obj == hiloxObject) || (obj != null && hiloxObject != null && hiloxObject.equals(obj));

        if (!isSatisfied) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 不相等校验, 相等抛异常
     * @param obj
     * @param errorCode
     * @param <HiloxObject>
     * @return
     */
    public <HiloxObject extends Object> EnsureParamObject isNotEqualTo(HiloxObject obj, String errorCode) {
        if (obj != hiloxObject) {
            if (obj != null) {
                isSatisfied = !obj.equals(hiloxObject);
            } else if (hiloxObject != null) {
                isSatisfied = !hiloxObject.equals(obj);
            } else {
                isSatisfied = false;
            }
        } else {
            isSatisfied = false;
        }

        if (!isSatisfied) {
            createHiloxException(errorCode);
        }
        return this;
    }
}
