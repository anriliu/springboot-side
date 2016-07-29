package com.github.yingzhuo.springboot.side.ssh2.core;

public interface ShellExecutor {

    /**
     * 调用脚本
     *
     * @param shellPath 脚本目录
     */
    public void execute(String shellPath);

    /**
     * 调用脚本
     *
     * @param shellPath 脚本目录
     * @param args      脚本参数
     */
    public void execute(String shellPath, String... args);

}
