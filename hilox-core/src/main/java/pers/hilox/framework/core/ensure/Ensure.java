package pers.hilox.framework.core.ensure;

import pers.hilox.framework.core.ensure.extensions.EnsureParamBoolean;

/**
 * 参数校验类实例化类
 * @auther hilox
 * @since 1.0
 */
public class Ensure {

    /**
     * 布尔参数校验类实例化
     * @param hiloxObject 参数
     * @param <HiloxObject> 参数类型
     * @return
     */
    public static <HiloxObject extends Boolean> EnsureParamBoolean that(HiloxObject hiloxObject) {
        return new EnsureParamBoolean(hiloxObject);
    }
}
