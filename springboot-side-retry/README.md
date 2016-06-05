# springboot-side-retry

在实际工作中，重试是一个经常使用的阶段。如使用第三方的网络服务，可能因为网络波动出现超时而失败。`spring-retry`是一种比较优雅的实现。

依赖
---

```xml
<dependency>
    <groupId>com.github.yingzhuo</groupId>
    <artifactId>springboot-side-retry</artifactId>
    <version>1.0.0</version>
</dependency>
```

配置
---

```properties
# 配置RetryTemplate 默认为true
springboot.side.retry-template.enabled=true

# 重试间隔时间
springboot.side.retry-template.back-off-period=2000

# 重试最大次数
springboot.side.retry-template.max-attempts=3
```

使用例子
---

```java
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	@Autowired
	private RetryTemplate retryTemplate;

    @Retryable(value = {SomeException.class}, maxAttempts = 3, backoff = @Backoff(2000L))
    public void doSomething() {
        // 业务方法
    }


    @Recover
    public void ex(SomeException.class) {
        // handle exception
    }
}
```

参考文档
---

* [官方文档](https://github.com/spring-projects/spring-retry)
* [网友博文](https://www.javacodegeeks.com/2014/12/spring-retry-ways-to-integrate-with-your-project.html)