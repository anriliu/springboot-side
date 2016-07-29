package com.github.yingzhuo.springboot.side.ssh2.core;

public class MockShellExecutor implements ShellExecutor {

    @Override
    public void execute(String shellPath) {
        // NOP
    }

    @Override
    public void execute(String shellPath, String... args) {
        // NOP
    }

}
