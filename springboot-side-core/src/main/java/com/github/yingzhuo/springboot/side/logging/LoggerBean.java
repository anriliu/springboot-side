package com.github.yingzhuo.springboot.side.logging;

import org.springframework.boot.logging.LogLevel;

public interface LoggerBean {

    public void log(LogLevel level, String format, Object... args);

    public void log(String format, Object... args);
}
