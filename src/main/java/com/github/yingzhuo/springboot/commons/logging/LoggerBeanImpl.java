package com.github.yingzhuo.springboot.commons.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerBeanImpl implements LoggerBean {

    private Logger logger;
    private LogLevel defaultLevel;

    public LoggerBeanImpl(String name, LogLevel level) {
        this.logger = LoggerFactory.getLogger(name);
        this.defaultLevel = level;
    }

    public void log(LogLevel level, String format, Object... args) {
        switch (level) {
            case TRACE:
                logger.trace(format, args);
                break;

            case DEBUG:
                logger.debug(format, args);
                break;

            case INFO:
                logger.info(format, args);
                break;

            case WARN:
                logger.warn(format, args);
                break;

            case ERROR:
            case FATAL:
                logger.error(format, args);
                break;
        }
    }

    public void log(String format, Object... args) {
        log(this.defaultLevel, format, args);
    }

}
