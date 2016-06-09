package com.github.yingzhuo.springboot.side.chanzor;

import java.util.Collection;

public class MockChanzorManager implements ChanzorManager {

    @Override
    public void send(String phoneNumber, String content) {
        // 无动作
    }

    @Override
    public void send(Collection<String> phoneNumbers, String content) {
        // 无动作
    }

    @Override
    public long overage() {
        return Long.MAX_VALUE;
    }

}
