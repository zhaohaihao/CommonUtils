package pers.hilox.framework.core.ensure.extensions;

import pers.hilox.framework.core.ensure.EnsureParam;
import pers.hilox.framework.core.utils.StringUtils;

/**
 * String参数校验类
 * @author hilox
 * @since 1.0
 */
public class EnsureParamString extends EnsureParam<String> {

    public EnsureParamString(String string) {
        super(string);
    }

    /**
     * 空校验, 为null抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamString isNotNull(String errorCode) {
        if (hiloxObject == null) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 空校验, 为null或者长度为0抛异常
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamString isNotEmpty(String errorCode) {
        if (StringUtils.isEmpty(hiloxObject)) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 空校验, 为null或者长度为0或者由空白符构成抛异常 <br/>
     * eg: <br/>
     *   StringUtils.isBlank(" ") = true <br/>
     *   StringUtils.isBlank("\t \n \f \r") = true <br/>
     * 对于制表符、换行符、换页符和回车符StringUtils.isBlank()均识为空白符
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamString isNotBlank(String errorCode) {
        if (StringUtils.isBlank(hiloxObject)) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 相等校验, 不相等抛异常
     * @param comparedString 比较字符串
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamString isEqualTo(String comparedString, String errorCode) {
        if (!StringUtils.equals(hiloxObject, comparedString)) {
            createHiloxException(errorCode);
        }
        return this;
    }

    /**
     * 不相等校验, 相等抛异常
     * @param comparedString 比较字符串
     * @param errorCode 错误代码
     * @return
     */
    public EnsureParamString isNotEqualTo(String comparedString, String errorCode) {
        if (StringUtils.equals(hiloxObject, comparedString)) {
            createHiloxException(errorCode);
        }
        return this;
    }
}
