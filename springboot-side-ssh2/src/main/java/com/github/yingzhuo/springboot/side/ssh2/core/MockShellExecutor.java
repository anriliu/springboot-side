package com.github.yingzhuo.springboot.side.ssh2.core;

public class MockShellExecutor implements ShellExecutor {

    @Override
    public Integer execute(String shellPath) {
        return 0;
    }

    @Override
    public Integer execute(String shellPath, String... args) {
        return 0;
    }

}
