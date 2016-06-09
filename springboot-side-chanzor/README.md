# springboot-side-chanzor

畅卓是北京一家提供短消息发送服务的互联网公司。

* [主页](http://www.chanzor.com/)
* [后台管理程序](http://web.chanzor.com:8080/)

配置
---

```
springboot.side.chanzor.enabled=true
springboot.side.chanzor.mode=general
springboot.side.chanzor.account=account
springboot.side.chanzor.password=12345
```

**Note:** mode为mock时，只用于调试。不真正的发送短信。

注入
---

```java
public class MyService {

  /* 暂时只提供发送短信和短信余额查询两个功能 */
  @Autowired
  private ChanzorManager chanzorManager;

}
```