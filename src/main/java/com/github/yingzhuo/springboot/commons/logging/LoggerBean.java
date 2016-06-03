package com.github.yingzhuo.springboot.commons.logging;

import org.springframework.boot.logging.LogLevel;

/**
 * 日志记录组件
 */
public interface LoggerBean {

    public void log(LogLevel level, String format, Object... args);

    public void log(String format, Object... args);

}
