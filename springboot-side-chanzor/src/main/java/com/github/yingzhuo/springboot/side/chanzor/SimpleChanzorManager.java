package com.github.yingzhuo.springboot.side.chanzor;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleChanzorManager implements ChanzorManager {

    private String url = "http://sms.chanzor.com:8001/sms.aspx";
    private String account;
    private String password;

    @Override
    public void send(String phoneNumber, String content) {
        List<String> phoneNumbers = new ArrayList<>(1);
        phoneNumbers.add(phoneNumber);
        send(phoneNumbers, content);
    }

    @Override
    public void send(Collection<String> phoneNumbers, String content) {

        try {
            Document document = Jsoup.connect(url)
                    .data("action", "send")
                    .data("account", account)
                    .data("password", password)
                    .data("mobile", StringUtils.join(phoneNumbers, ','))
                    .data("content", new String(content.getBytes(), "UTF-8"))
                    .post();

            // 畅卓接口在成功时返回的接口 有一个拼写错误 ("Success" -> "Sucess")
            String result = document.select("returnstatus").text();

            if (! (StringUtils.equalsIgnoreCase(result, "success") || StringUtils.equalsIgnoreCase(result, "sucess"))) {
                final String message = document.select("message").text();
                throw new IOException(message);
            }

        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public long overage() {
        try {
            Document document = Jsoup.connect(url)
                    .data("action", "overage")
                    .data("account", account)
                    .data("password", password)
                    .post();

            // 畅卓接口在成功时返回的接口 有一个拼写错误 ("Success" -> "Sucess")
            // 本程序只好妥协一下
            String result = document.select("returnstatus").text();

            if (! (StringUtils.equalsIgnoreCase(result, "success") || StringUtils.equalsIgnoreCase(result, "sucess"))) {
                final String message = "无法获取剩余短信额度";
                throw new IOException(message);
            } else {
                return Long.parseLong(document.select("overage").text());
            }

        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
