package pers.hilox.framework.core.exceptions;

import pers.hilox.framework.core.context.ApplicationContextHelper;

/**
 * 异常工厂
 * @auther hilox
 * @since 1.0
 */
public class HiloxExceptionFactory {

    private static ExceptionDefinitions exceptionDefinitions;

    /**
     * 获取业务异常
     * @param errorCode 错误代码
     * @param args 异常描述参数
     * @return
     */
    public static HiloxBusinessException create(String errorCode, String... args) {
        String exceptionMessage = getExceptionDefinitions().getExceptionMessage(errorCode);

        if (args.length > 0) {
            String errorMessage = String.format(exceptionMessage, args);
            return new HiloxBusinessException(errorCode, errorMessage);
        }
        return new HiloxBusinessException(errorCode, exceptionMessage);
    }

    /**
     * 获取exceptionDefinitions对象
     * @return
     */
    private static ExceptionDefinitions getExceptionDefinitions() {
        if (exceptionDefinitions == null) {
            exceptionDefinitions = ApplicationContextHelper.getBean(ExceptionDefinitions.class);
        }
        return exceptionDefinitions;
    }
}
