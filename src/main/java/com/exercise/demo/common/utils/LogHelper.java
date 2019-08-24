package com.exercise.demo.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 18:20
 */
public class LogHelper {

    /**
     * 如果日志写不进去，猜测原因slf4j.Logger在生产环境中在上下文中可能找不到对应的配置
     * 将org.slf4j.Logger替换成org.apache.log4j.Logger，采用原生的包
     */
    private static final Logger INFO_NET_LOGGER = LogManager.getLogger("infoLog");
    private static final Logger WARN_NET_LOGGER = LogManager.getLogger("warmLog");
    private static final Logger ERROR_NET_LOGGER = LogManager.getLogger("errorLog");
    private static final String SEPARATOR_BEGIN = "[";
    private static final String SEPARATOR_END = "]";

    /**
     * Info 日志
     *
     * @param subCategory 小类
     * @param filter1     过滤条件1
     * @param filter2     过滤条件2
     * @param msg         日志内容
     */
    public static void info(String subCategory, String filter1, String filter2, String msg) {
        INFO_NET_LOGGER.info(SEPARATOR_BEGIN + subCategory + SEPARATOR_END
                + SEPARATOR_BEGIN + filter1 + SEPARATOR_END
                + SEPARATOR_BEGIN + filter2 + SEPARATOR_END
                + msg);
    }

    /**
     * 警告日志
     *
     * @param subCategory 小类
     * @param filter1     过滤条件1
     * @param filter2     过滤条件2
     * @param msg         日志内容
     */
    public static void warn(String subCategory, String filter1, String filter2, String msg) {
        WARN_NET_LOGGER.warn(SEPARATOR_BEGIN + subCategory + SEPARATOR_END
                + SEPARATOR_BEGIN + filter1 + SEPARATOR_END
                + SEPARATOR_BEGIN + filter2 + SEPARATOR_END
                + msg);
    }

    /**
     * 错误日志
     *
     * @param subCategory 小类
     * @param filter1     过滤条件1
     * @param filter2     过滤条件2
     * @param msg         日志内容
     * @param exception   异常
     */
    public static void error(String subCategory, String filter1, String filter2, String msg, Exception exception) {
        ERROR_NET_LOGGER.error(SEPARATOR_BEGIN + subCategory + SEPARATOR_END
                + SEPARATOR_BEGIN + filter1 + SEPARATOR_END
                + SEPARATOR_BEGIN + filter2 + SEPARATOR_END
                + msg, exception);
    }
}
