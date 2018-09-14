package pers.hilox.framework.core.exceptions;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import pers.hilox.framework.core.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * 异常定义
 * @auther hilox
 * @since 1.0
 */
public class ExceptionDefinitions {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    private Properties exceptionDefinitionProps;

    /**
     * 根据错误代码获取异常描述信息
     * @param errorCode 错误代码
     * @return 异常描述信息
     */
    public String getExceptionMessage(String errorCode) {
        final String CANNOT_FOUND_ERROR_CODE_MESSAGE_PATTERN = "系统错误 [ErrorType = ERROR_MESSAGE_DEFINITION, ErrorCode = %s]";

        String message = StringUtils.EMPTY;
        try {
            message = (String) getDefinitions().get(errorCode);
        } catch (Exception e) {
            logger.error(String.format("Error message for [code = %s] is not defined", errorCode));
        }

        if (StringUtils.isEmpty(message)) {
            message = String.format(CANNOT_FOUND_ERROR_CODE_MESSAGE_PATTERN, errorCode);
        }

        return message;
    }

    /**
     * 获取properties
     * @return properties
     * @throws IOException
     */
    private Properties getDefinitions() throws IOException {
        if (exceptionDefinitionProps == null) {
            Resource[] resources = resourcePatternResolver.getResources("classpath*:/props/error.properties");
            exceptionDefinitionProps = new Properties();
            for (Resource resource : resources) {
                InputStream inputStream = resource.getInputStream();
                try {
                    Reader reader = new InputStreamReader(inputStream, "UTF-8");
                    try {
                        exceptionDefinitionProps.load(reader);
                    } finally {
                        reader.close();
                    }
                } finally {
                    inputStream.close();
                }
            }
        }
        return exceptionDefinitionProps;
    }
}
