# springboot-side-chanzor

依赖
---

```xml
<dependency>
    <groupId>com.github.yingzhuo</groupId>
    <artifactId>springboot-side-ssh2</artifactId>
    <version>1.0.0</version>
</dependency>
```

配置
---

```
springboot.side.ssh2.enabled=true
springboot.side.ssh2.mode=general
springboot.side.ssh2.hostname=10.211.55.18
springboot.side.ssh2.port=22
springboot.side.ssh2.username=yingzhuo
springboot.side.ssh2.password=133810
```

> **注意:** mode为mock时，只用于调试。不真正调用远程脚本。

注入
---

```java
public class MyService {

  @Autowired
  private ShellExecutor shellExecutor;

}
```