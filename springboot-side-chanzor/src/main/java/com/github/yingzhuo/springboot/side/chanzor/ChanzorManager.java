package com.github.yingzhuo.springboot.side.chanzor;

import java.util.Collection;

public interface ChanzorManager {

    /**
     * 发送短信息
     *
     * @param phoneNumber 电话号码
     * @param content     短信内容
     */
    public void send(String phoneNumber, String content);

    /**
     * 发送短消息
     *
     * @param phoneNumbers 电话号码
     * @param content      短信内容
     */
    public void send(Collection<String> phoneNumbers, String content);

    /**
     * 查看剩余短信额度
     *
     * @return 剩余短信额度
     */
    public long overage();

}
